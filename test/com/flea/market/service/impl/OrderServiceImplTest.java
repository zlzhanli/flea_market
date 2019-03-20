package com.flea.market.service.impl;

import com.flea.market.entity.OrderView;
import com.flea.market.service.OrderItemsService;
import com.flea.market.service.OrderService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    @Resource(name = "orderService")
    private OrderService orderService;

    @Test
    public void getOrderView() {
        OrderView orderView = orderService.getOrderView(1);
        System.out.println(orderView.getShippingMethod());
    }
}