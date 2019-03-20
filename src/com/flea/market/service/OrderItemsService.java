package com.flea.market.service;

import com.flea.market.pojo.OrderItems;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/11
 */
public interface OrderItemsService {

    /**
     * 通过orderId获取所有订单详情
     * @param orderId 订单ID
     * @return
     */
    @AutoClose
    @AutoCommit
    List<OrderItems> listByOrderId(Integer orderId);

}
