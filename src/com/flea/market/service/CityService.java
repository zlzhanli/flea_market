package com.flea.market.service;

import com.flea.market.pojo.City;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

public interface CityService {
    /**
     * 通过父级id 返回CIty
     * @param pId 父id
     * @return 城市列表
     */
    @AutoCommit
    @AutoClose
    List<City> listByPid(Integer pId);
}
