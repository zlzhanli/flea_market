package com.flea.market.service.impl;

import com.flea.market.service.GoodsStatusService;
import com.flea.market.service.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsStatusServiceImplTest {
    GoodsStatusService goodsStatusService = (GoodsStatusService) ServiceFactory.createFactory().create("GoodsStatusService");

    @Test
    public void list() {
    }

    @Test
    public void findById() {
        System.out.println(goodsStatusService.findById(1));
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void remove() {
    }
}