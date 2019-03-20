package com.flea.market.service.impl;

import com.flea.market.pojo.Cart;
import com.flea.market.pojo.Customer;
import com.flea.market.service.CartService;
import com.flea.market.service.ServiceFactory;
import org.junit.Test;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/8
 */
public class CartServiceImplTest {

    CartService cartService = (CartService) ServiceFactory.createFactory().create("cartService");

    @Test
    public void addTest(){
        Customer customer = new Customer();
        customer.setId(1);
        cartService.add(4, customer);
    }

    @Test
    public void updateNumTest(){
        cartService.updateNum(77,0,1);
    }

    @Test
    public void listTest(){
        List<Cart> ls = cartService.lsCarts(1);
        System.out.println(ls);
    }

    @Test
    public void deleteTest(){
        Cart cart = new Cart();
        cart.setId(2);
        cartService.delete(cart);
    }

}
