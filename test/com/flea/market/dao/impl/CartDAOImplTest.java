package com.flea.market.dao.impl;

import com.flea.market.dao.CartDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.Cart;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;

import static org.junit.Assert.*;

public class CartDAOImplTest {

    private CartDAO cartDAO = (CartDAO) DAOFactory.createFactory().create("cartDAO");

    @Test
    public void list() {
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() throws IllegalAccessException, InstantiationException {
//        System.out.println(Cart.class.newInstance());
        System.out.println(cartDAO.findById(77));
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
        Cart cart = new Cart();
        cart.setNum(5);
        cart.setId(77);
        cartDAO.update(cart);
    }

    @Test
    public void count() {

    }

    @Test
    public void getCartByCstomerAndGoods() {
    }

    @Test
    public void list1() {
    }

    @Test
    public void findById1() {
        System.out.println(cartDAO.findById(4));
    }
}