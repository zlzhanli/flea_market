package com.flea.market.utils;

import com.flea.market.pojo.Customer;
import com.flea.market.util.CardNumUtils;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author LiuTianyou
 * @date 2019/3/6
 */

public class cardNumUtilsTest {
    @Test
    public void cardNum() throws IOException {
        //String name= "45102519800411512X";
        String name= "622628199611015293";
        //CardNumUtils.checkCardNum(name);
    }
    @Test
    public void cardNum1() throws IOException {
        Customer customer = new Customer();
        customer.setUserBalance(new BigDecimal(12));
        System.out.println(JSONObject.fromObject(customer));
    }


}
