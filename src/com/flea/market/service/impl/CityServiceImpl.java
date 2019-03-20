package com.flea.market.service.impl;

import com.flea.market.dao.CityDAO;
import com.flea.market.pojo.City;
import com.flea.market.service.CityService;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author LiuTianyou
 * @date 2019/3/6
 */

public class CityServiceImpl implements CityService {
    @Resource(name = "cityDAO")
    private CityDAO cityDAO;

    @Override
    public List<City> listByPid(Integer pId) {
        return cityDAO.listByPid(pId);
    }
}
