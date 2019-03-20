package com.flea.market.dao;

import com.flea.market.dao.base.BaseDAO;
import com.flea.market.pojo.City;

import java.util.List;

public interface CityDAO extends BaseDAO<City> {
    /**
     * 通过父级id查找cities
     *
     * @param id 父id
     * @return
     */
    List<City> listByPid(Integer id);
}
