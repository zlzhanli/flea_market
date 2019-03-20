package com.flea.market.service;

import com.flea.market.entity.Result;
import com.flea.market.pojo.GoodsStatus;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public interface GoodsStatusService {

    /**
     * 查询所有商品类型
     * @return list
     */
    @AutoClose
    @AutoCommit
    List<GoodsStatus> list();

    /**
     * 根据id获取商品状态
     * @param id id
     * @return result
     */
    @AutoClose
    @AutoCommit
    GoodsStatus findById(Integer id);

    /**
     * 添加
     * @param goodsStatus status
     * @return result
     */
    @AutoClose
    @AutoCommit
    Result<GoodsStatus> add(GoodsStatus goodsStatus);

    /**
     * 修改
     * @param goodsStatus status
     * @return result
     */
    @AutoClose
    @AutoCommit
    Result<GoodsStatus> update(GoodsStatus goodsStatus);

    /**
     * 删除
     * @param id id
     * @return result
     */
    @AutoClose
    @AutoCommit
    Result<GoodsStatus> remove(Integer id);

}
