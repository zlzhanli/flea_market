package com.flea.market.service.impl;

import com.flea.market.dao.OrderItemsDAO;
import com.flea.market.pojo.OrderItems;
import com.flea.market.service.OrderItemsService;
import com.flea.market.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/11
 */
public class OrderItemsServiceImpl implements OrderItemsService {

    @Resource(name = "orderItemsDAO")
    private OrderItemsDAO  orderItemsDAO;

    @Override
    public List<OrderItems> listByOrderId(Integer orderId) {
        List<OrderItems> orderItemsList = orderItemsDAO.listByOrderId(orderId);
        return orderItemsList;
    }
}
