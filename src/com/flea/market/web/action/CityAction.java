package com.flea.market.web.action;

import com.flea.market.service.CityService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiuTianyou
 * @date 2019/3/6
 */

public class CityAction {
    @Resource(name = "cityService")
    private CityService cityService;
    private Integer pId;

    public String getCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSONArray.fromObject(cityService.listByPid(pId)).toString();
    }
    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
