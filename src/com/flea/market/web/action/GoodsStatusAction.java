package com.flea.market.web.action;


import com.flea.market.entity.Result;
import com.flea.market.pojo.GoodsStatus;
import com.flea.market.service.GoodsStatusService;
import com.flea.market.util.JSONUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;

/**
 * @author SunTao
 * @Date 2019/3/13
 */
public class GoodsStatusAction {
    @Resource(name = "GoodsStatusService")
    private GoodsStatusService goodsStatusService;

    private Integer id;
    private String statusName;
    private String text;

    /**
     * 获取商品状态的集合
     * @param request
     * @param response
     * @return
     */
    public String list(HttpServletRequest request, HttpServletResponse response) {
        List<GoodsStatus> goodsStatusList = goodsStatusService.list();
        return JSONArray.fromObject(goodsStatusList).toString();
    }

    /**
     * 更新商品状态数据的方法，包括添加和修改
     * @param request
     * @param response
     * @return
     */
    public String updateStatus(HttpServletRequest request, HttpServletResponse response) {
        GoodsStatus goodsStatus = new GoodsStatus();
        goodsStatus.setId(id);
        goodsStatus.setStatusName(statusName);
        goodsStatus.setText(text);
        Result<GoodsStatus> result = goodsStatusService.add(goodsStatus);
        return JSONUtil.toJson1(result).toString();
    }

    /**
     * 删除商品状态的方法
     * @param request
     * @param response
     * @return
     */
    public String deleteGoodsStatus(HttpServletRequest request, HttpServletResponse response) {
        Result<GoodsStatus> result = goodsStatusService.remove(id);
        return JSONUtil.toJson1(result).toString();
    }

    /**
     * 通过id获取商品状态的方法
     * @param request
     * @param response
     * @return
     */
    public String findById(HttpServletRequest request, HttpServletResponse response) {
        GoodsStatus goodsStatus = goodsStatusService.findById(id);
        return JSONObject.fromObject(goodsStatus).toString();
    }
}
