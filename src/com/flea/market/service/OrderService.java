package com.flea.market.service;

import com.flea.market.entity.OrderView;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.Order;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/11
 */
public interface OrderService {

    /**
     * 展示订单
     * @return
     */
    @AutoCommit
    @AutoClose
    List<Order> listOrder(Integer customerId);

    /**
     * 查询订单对象
     * @param orderId
     * @return
     */
    @AutoCommit
    @AutoClose
    Order findById(Integer orderId);


    /**
     * 创建订单
     * @param cartIds
     * @param customer
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<Order> createOrder(Integer[] cartIds, Customer customer, Integer type, Integer address);
    @AutoCommit
    @AutoClose
    OrderView getOrderView(Integer customerId);

}
