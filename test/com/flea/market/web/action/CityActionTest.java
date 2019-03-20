package com.flea.market.web.action;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CityActionTest {

    @Test
    public void getCity() throws IllegalAccessException, IOException, InstantiationException {
        CityAction  city = (CityAction) ActionFactory.createFactory().create("city");
        city.setpId(1);
        System.out.println(city.getCity(null,null));
    }
}