package com.flea.market.dao.impl;

import com.flea.market.dao.GoodsImgDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.GoodsImg;

import java.util.List;

/**
 * @author: zhh
 * @time: 2019/3/12 9:33
 */

public class GoodsImgDAOImpl extends SupBaseDAO<GoodsImg> implements GoodsImgDAO {

    @Override
    public List<GoodsImg> listByGoodsId(Integer id) {
        return list("select * from goods_img where goodsid = ?",GoodsImg.class,id);
    }

    @Override
    public List<GoodsImg> list() {
        String sql = "select * from goods_img";
        return list(sql,GoodsImg.class);
    }

    @Override
    public List<GoodsImg> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return null;
    }

    @Override
    public GoodsImg findById(Integer id) {
        return null;
    }

    @Override
    public void save(GoodsImg obj) {

    }

    @Override
    public void delete(GoodsImg obj) {

    }

    @Override
    public void remove(GoodsImg obj) {

    }

    @Override
    public void update(GoodsImg obj) {

    }

    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        return null;
    }
}
