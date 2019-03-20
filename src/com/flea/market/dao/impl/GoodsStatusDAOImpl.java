package com.flea.market.dao.impl;

import com.flea.market.dao.GoodsStatusDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.GoodsStatus;

import java.util.List;

/**
 * @author: zhh pass
 * @time: 2019/3/7 14:47
 */

public class GoodsStatusDAOImpl extends SupBaseDAO<GoodsStatus> implements GoodsStatusDAO {
    /**
     * zhh pass
     */
    @Override
    public List<GoodsStatus> list() {
        String sql = "select * from goods_status";
        return list(sql,GoodsStatus.class);
    }

    @Override
    public List<GoodsStatus> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return listByPage(detachedCriteria,begin,pageSize);
    }
    /**
     * zhh pass
     */
    @Override
    public GoodsStatus findById(Integer id) {
        String sql = "select * from goods_status where id = ?";
        return find(sql,GoodsStatus.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(GoodsStatus obj) {

        String sql = "insert into goods_status values(null,?,?)";
        execute(sql,obj.getStatusName(),obj.getText());
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(GoodsStatus obj) {
        String sql = "delete from goods_status where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(GoodsStatus obj) {

    }
    /**
     * zhh pass
     */
    @Override
    public void update(GoodsStatus obj) {
        String sql = "update goods_status set status_name=?,text=? where id=?";
        execute(sql,obj.getStatusName(),obj.getText(),obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) as count from goods_status";
        return count(sql,detachedCriteria);
    }
}
