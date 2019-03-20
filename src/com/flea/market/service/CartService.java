package com.flea.market.service;

import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Cart;
import com.flea.market.pojo.Customer;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/8
 */
public interface CartService {

    /**
     * 修改购物车中的商品数量
     * @param cartId
     * @param tag
     * @param num
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<Cart> updateNum(Integer cartId, Integer tag, Integer num);

    /**
     * 根据看客户id  查看相对的购物车全部信息
     * @param customerId
     * @return
     */
    @AutoCommit
    @AutoClose
    List<Cart> lsCarts(Integer customerId);


    /**
     * 购物车添加商品
     * @param goodsId  根据商品id，添加对应商品
     * @param customer 根据客户id 获取对应用户
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<Cart> add(Integer goodsId, Customer customer);


    /**
     * 删除购物车中的对应商品
     * @param cart
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<Cart> delete(Cart cart);

    @AutoCommit
    @AutoClose
    Cart findById(Integer cartId);

    @AutoCommit
    @AutoClose
    Integer countByCart(DetachedCriteria criteria);


}
