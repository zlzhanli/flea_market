package com.flea.market.dao.impl;

import com.flea.market.dao.OrderDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.Order;
import com.flea.market.util.SnUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDAOImplTest {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.createFactory().create("orderDAO");
    @Test
    public void list() {
        List<Order> list = orderDAO.list();
        System.out.println(orderDAO);
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
        System.out.println(orderDAO.findById(1));
    }

    @Test
    public void save() {
        Order order = new Order();
        orderDAO.save(order);
        System.out.println(order.getId());
        System.out.println(order);
    }

    @Test
    public void save2(){
         Order order = new Order(null,null, SnUtil.getOrderSn(),new BigDecimal(34),null,null,1,null,200);
        orderDAO.save(order);
        System.out.println(order.getId());
        System.out.println(order);
    }

    @Test
    public void delete() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
    }

    @Test
    public void count() {
    }
}