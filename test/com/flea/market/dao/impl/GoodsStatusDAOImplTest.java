package com.flea.market.dao.impl;

import com.flea.market.dao.GoodsStatusDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.pojo.GoodsStatus;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsStatusDAOImplTest {

    @Test
    public void list() {
        GoodsStatusDAO goodsStatusDAO = (GoodsStatusDAO) DAOFactory.createFactory().create("goodsStatusDAO");
        System.out.println(goodsStatusDAO.list());
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
        GoodsStatusDAO goodsStatusDAO = (GoodsStatusDAO) DAOFactory.createFactory().create("goodsStatusDAO");
        System.out.println(goodsStatusDAO.findById(1));
    }

    @Test
    public void save() {
        GoodsStatusDAO goodsStatusDAO = (GoodsStatusDAO) DAOFactory.createFactory().create("goodsStatusDAO");
        GoodsStatus goodsStatus = new GoodsStatus();
        goodsStatus.setStatusName("jfishf");
        goodsStatusDAO.save(goodsStatus);
    }

    @Test
    public void delete() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
        GoodsStatusDAO goodsStatusDAO = (GoodsStatusDAO) DAOFactory.createFactory().create("goodsStatusDAO");
        GoodsStatus goodsStatus = new GoodsStatus();
        goodsStatus.setStatusName("rwerwer");
        goodsStatus.setId(1);
        goodsStatusDAO.update(goodsStatus);
    }

    @Test
    public void count() {
        GoodsStatusDAO goodsStatusDAO = (GoodsStatusDAO) DAOFactory.createFactory().create("goodsStatusDAO");
        System.out.println(goodsStatusDAO.count(new DetachedCriteria()));
    }
}