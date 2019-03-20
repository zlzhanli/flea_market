package com.flea.market.service;

import com.flea.market.entity.Result;
import com.flea.market.pojo.GoodsType;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/8
 */
public interface GoodsTypeService {
    /**
     * 查询所有类型
     * @return list
     */
    @AutoClose
    @AutoCommit
    List<GoodsType> list();

    /**
     * 根据goodsTypeId查询goodsType对象
     * @param id
     * @return
     */
    @AutoCommit
    @AutoClose
    GoodsType findById(Integer id);

    /**
     * 添加商品类型
     * @param goodsType
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<GoodsType> saveGoodsType(GoodsType goodsType);


    /**
     * 删除商品类型
     * @param goodsType
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<GoodsType> deleteGoodsType(Integer goodsTypeId);


    /**
     * 修改商品类型
     * @param goodsType
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<GoodsType> updateGoodsType(GoodsType goodsType);
}
