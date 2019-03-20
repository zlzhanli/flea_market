package com.flea.market.dao.impl;

import com.flea.market.dao.OrderItemsDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.OrderItems;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderItemsDAOImplTest {

    OrderItemsDAO orderItemsDAO = (OrderItemsDAO) DAOFactory.createFactory().create("orderItemsDAO");
    @Test
    public void save() {
        System.out.println(orderItemsDAO.list());
    }

    @Test
    public void findByOrderId() {
        System.out.println(orderItemsDAO.findByOrderId(2));
    }

    @Test
    public void deleteTest(){
        OrderItems orderItems = new OrderItems();
        orderItems.setId(1);
        orderItemsDAO.delete(orderItems);
    }
}