package com.flea.market.dao.impl;

import com.flea.market.dao.ShippingMethodDAO;
import com.flea.market.dao.base.BaseDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.ShippingMethod;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/13
 */
public class ShippingMethodDAOImpl extends SupBaseDAO<ShippingMethod> implements ShippingMethodDAO {
    @Override
    public List<ShippingMethod> list() {
        String sql = "select * from shipping_method";
        return list(sql, ShippingMethod.class);
    }

    @Override
    public List<ShippingMethod> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return null;
    }

    @Override
    public ShippingMethod findById(Integer id) {
        String sql = "select * from shipping_method where id = ?";
        return find(sql,ShippingMethod.class,id);
    }

    @Override
    public void save(ShippingMethod obj) {
        String sql = "insert into shipping_method values(null,?,?);";
        Integer id = execute(sql,obj.getMethod(),obj.getMessage());
        obj.setShippingId(id);

    }

    @Override
    public void delete(ShippingMethod obj) {
        String sql = "delete from shipping_method where id = ?";
        execute(sql,obj.getShippingId());
    }

    @Override
    public void remove(ShippingMethod obj) {

    }

    @Override
    public void update(ShippingMethod obj) {
        String sql = "update shipping_method set method = ?, message = ? where id = ?";
        execute(sql,obj.getMethod(),obj.getMessage(),obj.getShippingId());

    }

    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        return null;
    }
}
