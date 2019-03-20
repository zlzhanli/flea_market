package com.flea.market.dao.impl;

import com.flea.market.dao.OrderItemsDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.OrderItems;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/11
 */
public class OrderItemsDAOImpl extends SupBaseDAO<OrderItems> implements OrderItemsDAO {
    /**
     * zhh pass
     */
    @Override
    public List<OrderItems> list() {
        String sql = "select * from orderitems";
        return list(sql, OrderItems.class);
    }

    @Override
    public List<OrderItems> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return null;
    }
    /**
     * zhh pass
     */
    @Override
    public OrderItems findById(Integer id) {
        String sql = "select * from orderitems where id = ?";
        return find(sql, OrderItems.class, id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(OrderItems obj) {
        String sql = "insert into orderitems values(null,?,?,?,?)";
        execute(sql, obj.getOrderId(), obj.getGoodsId(), obj.getNum(), obj.getPrice());
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(OrderItems obj) {
        String sql = "delete from orderitems where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(OrderItems obj) {

    }

    @Override
    public void update(OrderItems obj) {

    }

    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public OrderItems findByOrderId(Integer orderId) {
        String sql = "select * from orderitems where orders = ?";
        return find(sql, OrderItems.class, orderId);
    }

    @Override
    public List<OrderItems> listByOrderId(Integer orderId) {
        String sql = "select * from orderitems where orders = ?";
        return list(sql, OrderItems.class, orderId);
    }
}
