package com.flea.market.dao.impl;

import com.flea.market.dao.OrderDAO;
import com.flea.market.dao.base.BaseDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Order;

import java.util.List;

/**
 * @author: zhh
 * @time: 2019/3/7 14:53
 */

public class OrderDAOImpl extends SupBaseDAO<Order> implements OrderDAO {
    /**
     * zhh pass
     */
    @Override
    public List<Order> list() {

        String sql = "select * from orders";
        return  list(sql,Order.class);
    }

    @Override
    public List<Order> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {

        return listByPage(detachedCriteria,begin,pageSize);
    }
    /**
     * zhh pass
     */
    @Override
    public Order findById(Integer id) {
        String sql = "select * from orders where id=?";
        return find(sql,Order.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(Order obj) {

        String sql = "insert into orders values(null,?,?,?,?,now(),?,?,?)";

        Integer id = execute(sql, obj.getGoodsId(),obj.getOrderSn(),obj.getOrderPrice(),obj.getShippingMethod(),
                obj.getOrderCustomerId(),obj.getOrderReceiverAddress(),obj.getOrderStatus());
        obj.setId(id);
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(Order obj) {
        String sql = "delete from order where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(Order obj) {

    }
    /**
     * zhh pass
     */
    @Override
    public void update(Order obj) {

        String sql = "update orders set goods_id=?,order_sn=?,order_price=?,shipping_method=?,order_gmt_create=?,order_customer_id=?,order_receiver_address where id=?";
        execute(sql,obj.getGoodsId(),obj.getOrderSn(),obj.getOrderPrice(),obj.getShippingMethod(),obj.getOrderGmtCreate(),obj.getOrderCustomerId(),obj.getOrderReceiverAddress(),obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) from orders";
        return count(sql,detachedCriteria);
    }

    @Override
    public List<Order> listByCustomerId(Integer customerId) {
        String sql = "select * from orders where order_customer_id = ?";
        return list(sql, Order.class, customerId);
    }
}
