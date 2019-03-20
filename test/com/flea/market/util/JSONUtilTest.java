package com.flea.market.util;

import com.flea.market.dao.CustomerDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.Customer;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonValueProcessor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JSONUtilTest {

    @Test
    public void toJson() throws ClassNotFoundException {
        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.createFactory().create("customerDAO");
        Customer customer = customerDAO.findById(1);
        System.out.println(customer);

        Map<String, JsonValueProcessor> processors = new HashMap<String, JsonValueProcessor>();

		processors.put("java.util.Date", new UtilDateProcessor("yyyy-MM-dd hh:mm:ss"));

        System.out.println(JSONUtil.toJson(customer, processors));
    }

    @Test
    public void toJson1() {
    }
}