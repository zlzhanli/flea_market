package com.flea.market.dao;

import com.flea.market.dao.base.BaseDAO;
import com.flea.market.pojo.Order;

import java.util.List;

/**
 * @author: zhh
 * @time: 2019/3/7 14:54
 */
public interface OrderDAO extends BaseDAO<Order> {
    List<Order> listByCustomerId(Integer customerId);
}
