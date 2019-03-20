package com.flea.market.web.action;

import com.flea.market.entity.OrderView;
import com.flea.market.pojo.*;
import com.flea.market.service.*;
import com.flea.market.util.Fordword;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/12
 */
public class OrderItermsAction {
    
    @Resource(name = "orderItemsService")
    private OrderItemsService orderItemsService;
    @Resource(name = "goodsService")
    private GoodsService goodsService;
    @Resource(name = "orderService")
    private OrderService orderService;
    @Resource(name = "customerService")
    private CustomerService customerService;
    @Resource(name = "receiverAddressService")
    private ReceiverAddressService receiverAddressService;


    private Integer orderId;

    @Fordword
    public String showOrderItems(HttpServletRequest request, HttpServletResponse response){


//        //通过传来的orderId获取到OrderItems对象集合
//        List<OrderItems> itemsList = orderItemsService.listByOrderId(orderId);
//
//        //通过orderId获取到order对象
//        Order order = orderService.findById(orderId);
//
//        //通过order的orderReceiverAddress获取地址信息
//        ReceiverAddress receiverAddress = receiverAddressService.getById(order.getOrderReceiverAddress());
//
//        //创建Goods的List集合 保存获取到的商品对象
//        List<Goods> goodsList = new ArrayList<>();
//        for (int i = 0; i < itemsList.size(); i++){
//            //通过orderItems的goodsId获取到goods
//            Goods goods = goodsService.findById(itemsList.get(i).getGoodsId());
//            //添加到集合
//            goodsList.add(goods);
//        }
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setPassword(null);
        OrderView view = orderService.getOrderView(orderId);
        view.setCustomer(customer);


        request.setAttribute("orderView", view);


        return "/jsp/customer/order_detil.jsp";
        
    }
    @Fordword
    public String show(HttpServletRequest request, HttpServletResponse response){

        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setPassword(null);
        OrderView view = orderService.getOrderView(orderId);
        view.setCustomer(customer);
        request.setAttribute("orderView", view);
        return "/jsp/customer/myorder_detil.jsp";

    }
    
}
