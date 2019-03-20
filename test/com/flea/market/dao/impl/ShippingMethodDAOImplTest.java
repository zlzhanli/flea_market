package com.flea.market.dao.impl;

import com.flea.market.dao.ShippingMethodDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.ShippingMethod;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShippingMethodDAOImplTest {

    ShippingMethodDAO shippingMethodDAO = (ShippingMethodDAO) DAOFactory.createFactory().create("shippingMethodDAO");
    @Test
    public void list() {
        System.out.println(shippingMethodDAO.list());
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
        ShippingMethod shippingMethod = shippingMethodDAO.findById(2);
        System.out.println(shippingMethod);
    }

    @Test
    public void save() {
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