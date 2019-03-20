package com.flea.market.service.impl;

import com.flea.market.dao.GoodsTypeDAO;
import com.flea.market.pojo.GoodsType;
import com.flea.market.service.GoodsTypeService;
import com.flea.market.service.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsTypeServiceImplTest {

    @Test
    public void list() {
        GoodsTypeService goodsTypeService = (GoodsTypeService) ServiceFactory.createFactory().create("goodsTypeService");
        System.out.println(goodsTypeService.list());
    }

    @Test
    public void findById() {
        GoodsTypeService goodsTypeService = (GoodsTypeService) ServiceFactory.createFactory().create("goodsTypeService");
        System.out.println(goodsTypeService.findById(1));
    }

    @Test
    public void update(){
        GoodsTypeService goodsTypeService = (GoodsTypeService) ServiceFactory.createFactory().create("goodsTypeService");
        GoodsType goodsType = new GoodsType();
        goodsType.setTypeId(5);
        goodsType.setTypeName("袜子");
        goodsType.setTypePid(11);
        goodsTypeService.updateGoodsType(goodsType);
    }
}