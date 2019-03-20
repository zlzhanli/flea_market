package com.flea.market.web.action;


import com.flea.market.service.GoodsService;
import com.flea.market.service.ServiceFactory;
import org.junit.Test;

/**
 * @author: zhh
 * @time: 2019/3/8 11:34
 */

public class GoodsActionTest {

    @Test
    public void findById() throws InstantiationException, IllegalAccessException {
        GoodsAction goodsAction = (GoodsAction) ActionFactory.createFactory().create("goods");
//        System.out.println(goodsAction.findById(null,null));
    }
}
