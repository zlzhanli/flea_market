package com.flea.market.service.impl;

import com.flea.market.dao.*;
import com.flea.market.entity.OrderView;
import com.flea.market.entity.Result;
import com.flea.market.pojo.*;
import com.flea.market.service.OrderService;
import com.flea.market.util.FLog;
import com.flea.market.util.SnUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/11
 */
public class OrderServiceImpl implements OrderService {

    @Resource(name = "orderDAO")
    private OrderDAO orderDAO;
    @Resource(name = "cartDAO")
    private CartDAO cartDAO;
    @Resource(name = "goodsDAO")
    private GoodsDAO goodsDAO;
    @Resource(name = "orderItemsDAO")
    private OrderItemsDAO orderItemsDAO;
    @Resource(name = "customerDAO")
    private CustomerDAO customerDAO;
    @Resource(name = "receiverAddressDAO")
    private ReceiverAddressDAO addressDAO;
    @Resource(name = "shippingMethodDAO")
    private ShippingMethodDAO shippingMethodDAO;

    @Override
    public List<Order> listOrder(Integer customerId) {
        List<Order> orderList = orderDAO.listByCustomerId(customerId);
        return orderList;
    }

    @Override
    public Order findById(Integer orderId) {
        Order order = orderDAO.findById(orderId);
        return order;
    }

    @Override
    public Result<Order> createOrder(Integer[] cartIds, Customer customer, Integer type, Integer address) {
        //1、创建订单（订单明细）2、计算总价3、维护库存4、清除购物车
        customer = customerDAO.findById(customer.getId());
        Result<Order> result = new Result<>();
        Cart[] carts = new Cart[cartIds.length];
        Goods[] goods = new Goods[cartIds.length];
        OrderItems[] orderItems = new OrderItems[cartIds.length];
        BigDecimal sum = new BigDecimal(0);

        for (int i = 0; i < cartIds.length; i++) {
            //查询购物车
            carts[i] = cartDAO.findById(cartIds[i]);
            if (carts[i] == null) {
                //为空
                result.setCode(0);
                result.setMsg("购物车为空，先去添加商品吧");
                return result;
            }

            //修改库存
            goods[i] = goodsDAO.findById(carts[i].getGoodsId());
            if (goods[i] != null) {
                goods[i].setGoodsNum(goods[i].getGoodsNum() - carts[i].getNum());
                goodsDAO.update(goods[i]);
            }

            //计算总价
            sum = sum.add(new BigDecimal(carts[i].getNum()).multiply(carts[i].getGoodsPrice()));

            //添加订单明细
            orderItems[i] = new OrderItems();
            orderItems[i].setGoodsId(goods[i].getId());
            orderItems[i].setNum(carts[i].getNum());
            orderItems[i].setPrice(carts[i].getGoodsPrice());

            // 删除购物车
            cartDAO.delete(carts[i]);
        }
        FLog.info("总价："+sum);
        //添加订单
        Order order = new Order(null, null, SnUtil.getOrderSn(), sum, type, null, customer.getId(), address, 1);
        //保存订单
        orderDAO.save(order);
        //保存订单明细
        for (int i = 0; i < orderItems.length; i++) {
            orderItems[i].setOrderId(order.getId());
            orderItemsDAO.save(orderItems[i]);
        }


        //支付
        if (customer.getUserBalance().compareTo(sum) == -1) {
            result.setMsg("余额不足");
            return result;
        } else {
            customer.setUserBalance(customer.getUserBalance().subtract(sum));
            customerDAO.update(customer);
        }

        result.setCode(200);
        return result;
    }


    @Override
    public OrderView getOrderView(Integer orderId) {
        OrderView view = new OrderView();
        Order order = orderDAO.findById(orderId);

        List<OrderItems> orderItems = orderItemsDAO.listByOrderId(order.getId());
        System.out.println("id"+order.getShippingMethod());
        ShippingMethod shippingMethod = shippingMethodDAO.findById(order.getShippingMethod());
        System.out.println("shippingMethod"+shippingMethod);

        for (OrderItems item : orderItems) {
            Goods goods = goodsDAO.findById(item.getGoodsId());
            item.setGoods(goods);
        }
        order.setOrderItems(orderItems);

        view.setOrder(order);
        ReceiverAddress address = addressDAO.findById(order.getOrderReceiverAddress());
        view.setReceiverAddress(address);
        view.setShippingMethod(shippingMethod);

        return view;
        /**
         * 1 根据客户id查询订单 返回订单list 遍历list
         * 2 使用orderId查询orderItems 返回list 遍历list
         * 3 根据goodsId查询goods实体 返回pojo 保存pojo至goods实体
         *
         */
    }
}
