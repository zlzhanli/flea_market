package com.flea.market.service;

import com.flea.market.entity.Result;
import com.flea.market.pojo.ShippingMethod;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/13
 */
public interface ShippingMethodService {

    /**
     * 获取配送方式的方法
     * @return
     */
    @AutoCommit
    @AutoClose
    List<ShippingMethod> list();

    /**
     * 删除配送方式的方法
     * @param id
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<ShippingMethod> delete(Integer id);

    /**
     * 更新配送方式的方法，包括添加和修改
     * @param shippingMethod
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<ShippingMethod> update(ShippingMethod shippingMethod);

    /**
     * 根据id获取配送方式的方法
     * @param id
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<ShippingMethod> findById(Integer id);

}
