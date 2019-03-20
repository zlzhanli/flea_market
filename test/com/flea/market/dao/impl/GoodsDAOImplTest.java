package com.flea.market.dao.impl;

import com.flea.market.dao.GoodsDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.Goods;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsDAOImplTest {

    private GoodsDAO goodsDAO = (GoodsDAO) DAOFactory.createFactory().create("goodsDAO");

    @Test
    public void list() {
        System.out.println(goodsDAO.list());
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void save() {
        Goods goods = new Goods();
        goods.setGoodsName("sahhasdofhaioi");
        goodsDAO.save(goods);
    }

    @Test
    public void delete() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
    }

    @Test
    public void count() {
    }
}