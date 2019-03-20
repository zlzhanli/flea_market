package com.flea.market.dao;

import com.flea.market.dao.base.BaseDAO;
import com.flea.market.pojo.GoodsImg;

import java.util.List;

/**
 * @author: zhh
 * @time: 2019/3/12 9:31
 */

public interface GoodsImgDAO extends BaseDAO<GoodsImg> {

    List<GoodsImg> listByGoodsId(Integer id);
}
