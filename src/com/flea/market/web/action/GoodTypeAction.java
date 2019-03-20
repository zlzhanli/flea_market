package com.flea.market.web.action;

import com.flea.market.entity.Result;
import com.flea.market.pojo.Goods;
import com.flea.market.pojo.GoodsType;
import com.flea.market.service.GoodsService;
import com.flea.market.service.GoodsTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author karl lee
 * @Date 2019/3/8
 */
public class GoodTypeAction {

    @Resource(name = "goodsTypeService")
    private GoodsTypeService goodsTypeService;

    private Integer typeId;
    private String typeName;
    private Integer typePid;

    public String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSONArray.fromObject(goodsTypeService.list()).toString();
    }

    public String findById(HttpServletRequest request, HttpServletResponse response){
        return JSONObject.fromObject(goodsTypeService.findById(typeId)).toString();
    }

    public String save(HttpServletRequest request, HttpServletResponse response){
        GoodsType goodsType = new GoodsType();
        goodsType.setTypeName(typeName);
        goodsType.setTypePid(typePid);
        Result result = goodsTypeService.saveGoodsType(goodsType);
        return JSONObject.fromObject(result).toString();
    }

    public String delete(HttpServletRequest request, HttpServletResponse response){
        Result result = goodsTypeService.deleteGoodsType(typeId);
        return JSONObject.fromObject(result).toString();
    }

    public String update(HttpServletRequest request, HttpServletResponse response){
        GoodsType goodsType = new GoodsType();
        goodsType.setTypeId(typeId);
        goodsType.setTypeName(typeName);
        goodsType.setTypePid(typePid);
        Result result = goodsTypeService.updateGoodsType(goodsType);
        return JSONObject.fromObject(result).toString();
    }
}
