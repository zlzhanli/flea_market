package com.flea.market.web.action;

import com.flea.market.pojo.Customer;
import org.junit.Test;

/**
 * @author zl
 * @time 2019/3/6
 */
public class CustomerActionTest {




    @Test
    public void findByIdTest() throws IllegalAccessException, InstantiationException {
        CustomerAction customerAction = (CustomerAction) ActionFactory.createFactory().create("customer");
        String customer = customerAction.findByIdCustomer(null,null);
        System.out.println(customer);
    }

    @Test
    public void saveCustomerTest() throws IllegalAccessException, InstantiationException {
        CustomerAction customerAction = (CustomerAction) ActionFactory.createFactory().create("customer");
        String customer = customerAction.save(null,null);
        System.out.println(customer);
    }

    @Test
    public void findCustomerTest() throws InstantiationException, IllegalAccessException {
        CustomerAction customerAction = (CustomerAction) ActionFactory.createFactory().create("customer");
        String s = customerAction.findCustomer(null,null);
        System.out.println(s.toString());

    }


    @Test
    public void list() throws InstantiationException, IllegalAccessException {
        CustomerAction customerAction = (CustomerAction) ActionFactory.createFactory().create("customer");
        System.out.println(customerAction.list(null,null));
    }
}
