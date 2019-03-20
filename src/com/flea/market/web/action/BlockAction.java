package com.flea.market.web.action;

import com.flea.market.entity.Result;
import com.flea.market.service.BlockService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;

/**
 * @author LiuTianyou
 * @date 2019/3/14
 */

public class BlockAction {
    Integer id;
    @Resource(name="blockService")
    private BlockService blockService;
    public String listBlock(HttpServletRequest request, HttpServletResponse response) {
        return JSONArray.fromObject( blockService.list()).toString();
    }
    public String findById(HttpServletRequest request, HttpServletResponse response){
        return JSONObject.fromObject(blockService.findById(id)).toString();

    }



}
