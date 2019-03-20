package com.flea.market.dao;

import com.flea.market.dao.base.BaseDAO;
import com.flea.market.pojo.OrderItems;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/11
 */
public interface OrderItemsDAO extends BaseDAO<OrderItems> {

    OrderItems findByOrderId(Integer orderId);

    List<OrderItems> listByOrderId(Integer orderId);

}
