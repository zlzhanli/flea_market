package com.flea.market.dao.impl;

import com.flea.market.dao.GoodsDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Goods;

import java.util.List;
import java.util.Map;

/**
 * @author: zhh pass
 * @time: 2019/3/7 14:45
 */

public class GoodsDAOImpl extends SupBaseDAO<Goods> implements GoodsDAO {

    @Override
    public List<Goods> list() {
        String sql = "select * from goods";
        return list(sql, Goods.class);
    }

    @Override
    public List<Goods> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        DetachedCriteria criteria = detachedCriteria.clone();
        Map<String, DetachedCriteria.Factor> map = criteria.getMap();
        DetachedCriteria criteria1 = new DetachedCriteria();

        for (String key : map.keySet()) {
            if (map.get(key) instanceof DetachedCriteria.Like) {

                criteria1.add(key, map.get(key));
                map.put(key, null);
            }
        }
        create("create view A as select * from `goods`", criteria1);
        criteria.setPageing(true, begin, pageSize);
        String sql = "select * from A";
        List<Goods> goods = listByPage(sql, Goods.class, criteria);
        execute(" drop view A");
        return goods;
    }

    @Override
    public Goods findById(Integer id) {
        String sql = "select * from goods where id = ?";
        return find(sql, Goods.class, id);
    }

    @Override
    public void save(Goods obj) {

        String sql = "insert into goods values(null, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?, ?, ?)";
        Integer id = execute(sql, obj.getGoodsName(), obj.getGoodsDetail(), obj.getGoodsCover(), obj.getGoodsFullName(),
                obj.getGoodsStatus(), obj.getGoodsType(), obj.getGoodsNum(), obj.getGoodsPrice(),
                obj.getGmtModified(), obj.getGoodsModifyReason(), obj.getGoodsSn(), obj.getGoodsSource(),
                obj.getShippingMethod(), obj.getGoodsKeywords(), obj.getGoodsPageView(), obj.getGoodsOwner());
        obj.setId(id);

    }

    @Override
    public void delete(Goods obj) {
        String sql = "delete from goods where id=?";
        execute(sql, obj.getId());
    }

    @Override
    public void remove(Goods obj) {
        String sql = "update goods set goods_status = ? " +
                " where id = ?";
        execute(sql, 6, obj.getId());


    }

    @Override
    public void update(Goods obj) {

        String sql = "update goods set goods_name = ?, goods_detail = ?, goods_cover = ?, goods_full_name = ?, " +
                "goods_status = ?, goods_type = ?, goods_num = ?, goods_price = ?, gmt_create = ?, gmt_modified = now()," +
                " goods_modify_reason = ?, goods_sn = ?,goods_source = ?, shipping_method = ?, goods_keywords = ?," +
                "goods_page_view = ?, goods_owner = ? " +
                "where id = ?";
        execute(sql, obj.getGoodsName(), obj.getGoodsDetail(), obj.getGoodsCover(), obj.getGoodsFullName(),
                obj.getGoodsStatus(), obj.getGoodsType(), obj.getGoodsNum(), obj.getGoodsPrice(), obj.getGmtCreate(),
                obj.getGoodsModifyReason(), obj.getGoodsSn(), obj.getGoodsSource(), obj.getShippingMethod(),
                obj.getGoodsKeywords(), obj.getGoodsPageView(), obj.getGoodsOwner(), obj.getId());
    }

    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        DetachedCriteria criteria = detachedCriteria.clone();
        Map<String, DetachedCriteria.Factor> map = criteria.getMap();
        DetachedCriteria criteria1 = new DetachedCriteria();

        for (String key : map.keySet()) {
            if (map.get(key) instanceof DetachedCriteria.Like) {

                criteria1.add(key, map.get(key));
                map.put(key, null);
            }
        }
        create("create view A as select * from `goods`", criteria1);
        String sql = "select count(*) as count from A";
        Integer count = count(sql, detachedCriteria);
        execute(" drop view A");
        return count;
    }
}
