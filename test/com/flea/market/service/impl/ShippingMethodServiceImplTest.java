package com.flea.market.service.impl;

import com.flea.market.service.ServiceFactory;
import com.flea.market.service.ShippingMethodService;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShippingMethodServiceImplTest {

    ShippingMethodService shippingMethodService = (ShippingMethodService) ServiceFactory.createFactory().create("shippingMethodService");
    @Test
    public void list() {
        System.out.println(shippingMethodService.list().toString());
    }
}