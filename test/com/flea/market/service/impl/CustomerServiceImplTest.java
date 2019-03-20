package com.flea.market.service.impl;

import com.flea.market.dao.CustomerDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.service.CustomerService;
import com.flea.market.service.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerServiceImplTest {

    CustomerService customerService = (CustomerService) ServiceFactory.createFactory().create("customerService");
    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setPassword("996541");
        customer.setNickName("无敌");
        customer.setLoginName("wudi");
        Result<Customer> save = customerService.save(customer);
        System.out.println(save);
    }

    @Test
    public void findById() {
        System.out.println(customerService.findById(38));
    }

    @Test
    public void findByLoginName() {
        System.out.println(customerService.findByLoginName("wudi"));
    }

    @Test
    public void update() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setLoginName("youxiu");
        customer.setNickName("优秀");
        System.out.println(customerService.update(customer));
    }

    @Test
    public void updatePhone() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setPhone("13833666699");
        System.out.println(customerService.updatePhone(customer));
    }

    @Test
    public void updateEmail() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setEmail("12306@163.com");
        System.out.println(customerService.updateEmail(customer));
    }

    @Test
    public void updateIdCard() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setIdCard("666661980121201630");
        System.out.println(customerService.updateIdCard(customer));
    }

    @Test
    public void updatePhoto() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setPhoto("666661980121201630.jpg");
        System.out.println(customerService.updatePhoto(customer));
    }

    @Test
    public void updateNickName() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setNickName("wobenyouxiu");
        System.out.println(customerService.updateNickName(customer));
    }

    @Test
    public void checkPwd() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setPassword("996541");
        System.out.println(customerService.checkPwd(customer));
    }

    @Test
    public void updatePwd() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setPassword("666321");
        System.out.println(customerService.updatePwd(customer));
    }

    @Test
    public void login() {
        Customer customer = new Customer();
        customer.setLoginName("youxiu");
        customer.setPassword("666321");
        System.out.println(customerService.login(customer));
    }

    @Test
    public void updateSex() {
        Customer customer = new Customer();
        customer.setId(38);
        customer.setSex("男");
        System.out.println(customerService.updateSex(customer));
    }

    @Test
    public void list() {
        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.createFactory().create("customerDAO");
        System.out.println(customerService.list());
        System.out.println(customerDAO.count(new DetachedCriteria()));
    }

    @Test
    public void search() {
        DetachedCriteria detachedCriteria = new DetachedCriteria();

        PageBean<Customer> search = customerService.search(detachedCriteria, 1, 5);
        System.out.println();
        System.out.println(search);
    }
}