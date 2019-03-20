package com.flea.market.dao.impl;

import com.flea.market.dao.CustomerStatusDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.CustomerStatus;

import java.util.List;

/**
 * @author: zhh pass
 * @time: 2019/3/7 14:40
 */

public class CustomerStatusDAOImpl extends SupBaseDAO<CustomerStatus> implements CustomerStatusDAO {
    /**
     * zhh pass
     */
    @Override
    public List<CustomerStatus> list() {
        String sql = "select * from customer_status";
        return list(sql,CustomerStatus.class);
    }

    @Override
    public List<CustomerStatus> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return listByPage(detachedCriteria,begin,pageSize);
    }
    /**
     * zhh pass
     */
    @Override
    public CustomerStatus findById(Integer id) {
        String sql = "select * from customer_status where id=?";
        return find(sql,CustomerStatus.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(CustomerStatus obj) {
        String sql = "insert into customer_status values(null,?,?)";
        execute(sql,obj.getCustomerStatusTitle(),obj.getCustomerStatusDesc());
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(CustomerStatus obj) {
        String sql = "delete from customer_status where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(CustomerStatus obj) {

    }
    /**
     * zhh pass
     */
    @Override
    public void update(CustomerStatus obj) {
        String sql = "update customer_status set customer_status_title=?,customer_status_desc=? where id=?";
        execute(sql,obj.getCustomerStatusTitle(),obj.getCustomerStatusDesc(),obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) from customer_status";
        return count(sql,detachedCriteria);
    }
}
