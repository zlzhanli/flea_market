package com.flea.market.dao.impl;

import com.flea.market.dao.GoodsTypeDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.GoodsType;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsTypeDAOImplTest {

    @Test
    public void list() {
        GoodsTypeDAO goodsTypeDAO = (GoodsTypeDAO) DAOFactory.createFactory().create("goodsTypeDAO");
        System.out.println(goodsTypeDAO.list());
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
        GoodsTypeDAO goodsTypeDAO = (GoodsTypeDAO) DAOFactory.createFactory().create("goodsTypeDAO");
        System.out.println(goodsTypeDAO.findById(1));
    }

    @Test
    public void save() {
        GoodsTypeDAO goodsTypeDAO = (GoodsTypeDAO) DAOFactory.createFactory().create("goodsTypeDAO");
        GoodsType goodsType = new GoodsType();
        goodsType.setTypeName("fhashdfi");
        goodsTypeDAO.save(goodsType);
    }

    @Test
    public void delete() {
        GoodsTypeDAO goodsTypeDAO = (GoodsTypeDAO) DAOFactory.createFactory().create("goodsTypeDAO");
        GoodsType goodsType = goodsTypeDAO.findById(7);
        goodsTypeDAO.delete(goodsType);
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
        GoodsTypeDAO goodsTypeDAO = (GoodsTypeDAO) DAOFactory.createFactory().create("goodsTypeDAO");
        GoodsType goodsType = new GoodsType();
        goodsType.setTypeId(5);
        goodsType.setTypePid(5);
        goodsTypeDAO.update(goodsType);
    }

    @Test
    public void count() {
    }
}