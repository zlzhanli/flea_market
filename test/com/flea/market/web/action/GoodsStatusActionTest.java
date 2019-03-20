package com.flea.market.web.action;

import com.flea.market.pojo.GoodsStatus;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsStatusActionTest {

    @Test
    public void list() {
    }

    @Test
    public void updateStatus() throws InstantiationException, IllegalAccessException {
        GoodsStatusAction goodsStatusAction = (GoodsStatusAction) ActionFactory.createFactory().create("goodsStatus");
        System.out.println(goodsStatusAction.updateStatus(null,null));

    }

    @Test
    public void deleteGoodsStatus() {
    }

    /**
     * 注意使用此条测试时要向该方法中给一个默认的id值
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void findById() throws InstantiationException, IllegalAccessException {
        GoodsStatusAction goodsStatusAction = (GoodsStatusAction) ActionFactory.createFactory().create("goodsStatus");
        System.out.println(goodsStatusAction.findById(null,null));
    }

    @Test
    public void list1() throws InstantiationException, IllegalAccessException {
        GoodsStatusAction goodsStatusAction = (GoodsStatusAction) ActionFactory.createFactory().create("goodsStatus");
        System.out.println(goodsStatusAction.list(null,null));
    }
}