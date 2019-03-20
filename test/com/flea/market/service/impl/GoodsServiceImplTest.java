package com.flea.market.service.impl;

import com.flea.market.dao.GoodsDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.pojo.Goods;
import com.flea.market.service.GoodsService;
import com.flea.market.service.ServiceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GoodsServiceImplTest {

    private GoodsService goodsService = (GoodsService) ServiceFactory.createFactory().create("goodsService");

    private GoodsDAO goodsDAO = (GoodsDAO) DAOFactory.createFactory().create("goodsDAO");
    @Test
    public void search() {
        DetachedCriteria criteria = new DetachedCriteria();
        criteria.add("goods_status", new DetachedCriteria.FactorImp<Integer>(1));

        PageBean<Goods> search = goodsService.search(criteria, 1, 10);
        System.out.println(search);
        List<Goods> goods = goodsDAO.listByPage(criteria, 0, 10);
        System.out.println(goods);
    }

    @Test
    public void listGoods() {
        System.out.println(goodsService.list());
    }

    @Test
    public void findById() {

        System.out.println(goodsService.findById(1).getList());
    }

    @Test
    public void save() {
        Goods good = new Goods();
        goodsService.save(good);
    }

}