package com.flea.market.dao.impl;

import com.flea.market.dao.GoodsTypeDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.GoodsType;

import java.util.List;

/**
 * @author: zhh pass
 * @time: 2019/3/7 14:49
 */

public class GoodsTypeDAOImpl extends SupBaseDAO<GoodsType> implements GoodsTypeDAO {
    /**
     * zhh pass
     */
    @Override
    public List<GoodsType> list() {
        String sql = "select * from goods_type";
        return list(sql,GoodsType.class);
    }

    @Override
    public List<GoodsType> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return listByPage(detachedCriteria,begin,pageSize);
    }
    /**
     * zhh pass
     */
    @Override
    public GoodsType findById(Integer id) {
        String sql = "select * from goods_type where type_id=?";
        return find(sql,GoodsType.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(GoodsType obj) {

        String sql = "insert into goods_type value(null,?,?,?,?)";
        execute(sql,obj.getTypeName(),obj.getTypePid(),
                obj.getTypePath(),obj.getTypeLv());
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(GoodsType obj) {
        String sql = "delete from goods_type where type_id=?";
        execute(sql,obj.getTypeId());
    }

    @Override
    public void remove(GoodsType obj) {

    }
    /**
     * zhh pass
     */
    @Override
    public void update(GoodsType obj) {

        String sql = "update goods_type set type_name=?,type_pid=?,type_path=?,type_lv=? where type_id=?";
        execute(sql,obj.getTypeName(),obj.getTypePid(),obj.getTypePath(),obj.getTypeLv(),obj.getTypeId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) as count from goods_type" ;
        return count(sql,detachedCriteria);
    }
}
