package com.flea.market.web.action;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShippingMethodActionTest {


    @Test
    public void getListMethod() throws InstantiationException, IllegalAccessException {
        ShippingMethodAction shippingMethodAction = (ShippingMethodAction) ActionFactory.createFactory().create("shippingMethod");
        System.out.println(shippingMethodAction.getListMethod(null,null));
    }
}