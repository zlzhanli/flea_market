package com.flea.market.dao.impl;

import com.flea.market.dao.CityDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.City;

import java.util.List;

/**
 * @author LiuTianyou
 * @date 2019/3/6
 */

public class CityDAOImpl extends SupBaseDAO<City> implements CityDAO {
    @Override
    public List<City> list() {
        String sql = "select * from city";
        return list(sql,City.class);
    }

    @Override
    public List<City> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return listByPage(detachedCriteria,begin,pageSize);
    }

    @Override
    public City findById(Integer id) {
        String sql = "select * from city where id=?";
        return find(sql,City.class,id);
    }

    @Override
    public void save(City obj) {
        String sql = "insert into city values(null,?,?,?)";
        Integer id = execute(sql,obj.getPid(),obj.getCityname(),obj.getType());
        obj.setId(id);
    }

    @Override
    public void delete(City obj) {

    }

    @Override
    public void remove(City obj) {

    }

    @Override
    public void update(City obj) {

        String sql = "update city set pid=?,cityname=?,type=?";
        execute(sql,obj.getPid(),obj.getCityname(),obj.getType(),obj.getId());
    }
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) from city";
        return count(sql,detachedCriteria);
    }
    @Override
    public List<City> listByPid(Integer id) {

        return list("select * from city where pid = ?", City.class, id);
    }
}
