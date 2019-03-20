package com.flea.market.service.impl;

import com.flea.market.entity.GoodsView;
import com.flea.market.pojo.Administrator;
import com.flea.market.service.AdministratorService;
import com.flea.market.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdministratorServiceImplTest {
    private AdministratorService administratorService;

    @Before
    public void before(){
        administratorService = (AdministratorService) ServiceFactory.createFactory().create("administratorService");
    }


    @Test
    public void testLogin(){
        administratorService = (AdministratorService) ServiceFactory.createFactory().create("administratorService");

        Administrator admin = new Administrator();
        admin.setLoginName("admin");
        admin.setPassword("123456");
        System.out.println(administratorService.login(admin));
    }

    @Test
    public void findGoodsTest() {
        administratorService = (AdministratorService) ServiceFactory.createFactory().create("administratorService");
        System.out.println(administratorService.findGood(41));
    }
}