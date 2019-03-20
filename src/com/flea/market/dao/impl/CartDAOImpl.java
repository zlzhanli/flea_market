package com.flea.market.dao.impl;

import com.flea.market.dao.CartDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Cart;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/8
 */
public class CartDAOImpl extends SupBaseDAO<Cart> implements CartDAO {
    @Override
    public List<Cart> list() {
        return null;
    }

    @Override
    public List<Cart> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return null;
    }

    @Override
    public Cart findById(Integer id) {
        String sql = "select * from cart where id = ?";
        return find(sql, Cart.class, id);
    }

    @Override
    public void save(Cart obj) {
        String sql = "insert into cart values(null,?,?,?,?,?,?)";
        execute(sql, obj.getGoodsName(), obj.getGoodsCover(), obj.getGoodsPrice(), obj.getNum(),
                obj.getCustomer().getId(), obj.getGoods().getId());
    }

    @Override
    public void delete(Cart obj) {
        String sql = "delete from cart where id = ?";
        execute(sql, obj.getId());
    }

    @Override
    public void remove(Cart obj) {

    }

    @Override
    public void update(Cart obj) {
        String sql = "update cart set num = ? where id = ?";
        execute(sql, obj.getNum(), obj.getId());
    }

    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(*) as count from cart";
        Integer count = count(sql, detachedCriteria);
        System.out.println("DAO :count=="+count);

        return count;


    }

    @Override
    public Cart getCartByCstomerAndGoods(Integer customerId, Integer goodsId) {
        String sql = "select * from cart where customer = ? and goods = ?";
        return find(sql, Cart.class, customerId, goodsId);
    }

    @Override
    public List<Cart> list(Integer customerId) {
        String sql = "select * from cart where customer = ?";
        return list(sql, Cart.class, customerId);
    }

}
