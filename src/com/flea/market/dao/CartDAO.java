package com.flea.market.dao;

import com.flea.market.dao.base.BaseDAO;
import com.flea.market.pojo.Cart;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/8
 */
public interface CartDAO extends BaseDAO<Cart> {

    Cart getCartByCstomerAndGoods(Integer customerId, Integer goodsId);

    List<Cart> list(Integer customerId);
}
