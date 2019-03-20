package com.flea.market.dao.impl;

import com.flea.market.dao.CustomerDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.pojo.Customer;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zl
 * @time 2019/3/6
 */
public class CustomerDAOImplTest {
    CustomerDAO customerDAO;

    @Test
    public void findByIdTest() {
        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.createFactory().create("customerDAO");
        Customer customer = customerDAO.findById(1);
        System.out.println(customer);
    }

    @Test
    public void saveCustomerTest() {
        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.createFactory().create("customerDAO");
        Customer customer = new Customer();
        customer.setNickName("asd");
        customer.setLoginName("assdgs_dsgf");
        customer.setPassword("1231213");
        customerDAO.save(customer);
    }

    @Test
    public void findByLoginNameTest() {
        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.createFactory().create("customerDAO");
        Customer customer = customerDAO.findByLoginName("lty");
        System.out.println(customer);
    }


    @Test
    public void updateCustomer() {
        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.createFactory().create("customerDAO");
        Customer customer = new Customer();
        Date date = new Date();
        BigDecimal userBalance = new BigDecimal(4);
        customer.setId(1);
        customer.setGmtCreate(date);
        customer.setGmtModified(date);
        customer.setUserBalance(userBalance);
        customer.setUserStatus(1);
        customer.setBirthday(date);
        customer.setProvince("北京");
        customer.setCity("北京");
        customer.setArea("海淀区");
        customer.setSex("男");
        customer.setEmail("12312322@qq.com");
        customer.setPhone("123456789");
        customer.setIdCard("410524199901010018");
        customer.setPassword("123");
        customer.setNickName("liutianyou");
        customer.setLoginName("lty");
        customer.setRealName("刘天佑");
        customerDAO.update(customer);
    }

    @Test
    public void test() {
        Customer likeguo = customerDAO.findByLoginNameOrPhoneOrEmail("lty", "lty", "lty");

        System.out.println(JSONObject.fromObject(likeguo));

        System.out.println(DigestUtils.md5Hex("123123"));
    }

    @Before
    public void before() {
        customerDAO = (CustomerDAO) DAOFactory.createFactory().create("customerDAO");
    }

    @After
    public void after() {

    }

    @Test
    public void list() {
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByLoginName() {
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
        System.out.println(customerDAO.count(new DetachedCriteria()));
    }

    @Test
    public void findByLoginNameOrPhoneOrEmail() {
    }

    @Test
    public void updatePhoto() {
    }

    @Test
    public void updatePwd() {
    }

    @Test
    public void updateNickName() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setNickName("12321");
        customerDAO.updateNickName(customer);

    }

    @Test
    public void listByPage1() {
        DetachedCriteria detachedCriteria = new DetachedCriteria();
        System.out.println(customerDAO.listByPage(detachedCriteria,6,5));
    }

    @Test
    public void count1() {

    }
}
