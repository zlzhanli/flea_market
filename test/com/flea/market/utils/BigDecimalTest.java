package com.flea.market.utils;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zl
 * @time 2019/3/14
 */
public class BigDecimalTest {

    @Test
    public void tese1(){
        System.out.println(new BigDecimal(100.34).multiply(new BigDecimal(43)));
        System.out.println(new BigDecimal(100.34).subtract(new BigDecimal(43)));
        System.out.println(new BigDecimal(100.34).add(new BigDecimal(43)));
        BigDecimal decimal = new BigDecimal(1212);
        decimal = decimal.add(new BigDecimal(34).multiply(new BigDecimal(32)));
        System.out.println(decimal);

    }
}
