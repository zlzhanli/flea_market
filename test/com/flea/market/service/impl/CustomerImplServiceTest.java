package com.flea.market.service.impl;

import com.flea.market.pojo.Customer;
import com.flea.market.service.CustomerService;
import com.flea.market.service.ServiceFactory;
import org.junit.Test;

/**
 * @author zl
 * @time 2019/3/6
 */
public class CustomerImplServiceTest {
    CustomerService customerService = (CustomerService) ServiceFactory.createFactory().create("customerService");
    @Test
    public void findByIdTest(){
        Customer customer = customerService.findById(1);
        System.out.println(customer);
    }

    @Test
    public void saveTest() {
        CustomerService customerService = (CustomerService) ServiceFactory.createFactory().create("customerService");
        Customer customer = new Customer();
        customer.setNickName("asd");
        customer.setLoginName("a_spa");
        customer.setPassword("123123");
        customerService.save(customer);
    }

    @Test
    public void updatePhoneTest(){
        Customer customer = new Customer();
        customer.setId(1);
        customer.setPhone("110");
        customerService.updatePhone(customer);
    }

    @Test
    public void updatePhotoTest(){
        Customer customer = new Customer();
        customer.setId(4);
        customer.setPhoto("b.jpg");
        customerService.updatePhoto(customer);
    }

    @Test
    public void updatePwdTest(){
        Customer customer = new Customer();
        customer.setId(14);
        customer.setPassword("123");
        customerService.updatePwd(customer);
    }


    @Test
    public void checkPwd(){
        Customer customer = new Customer();
        customer.setId(14);
        customer.setPassword("123");
        customerService.checkPwd(customer);
    }


}
