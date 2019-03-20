package com.flea.market.web.action;

import com.flea.market.entity.Result;
import com.flea.market.pojo.ShippingMethod;
import com.flea.market.service.ShippingMethodService;
import com.flea.market.util.JSONUtil;
import net.sf.json.JSONArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/13
 */
public class ShippingMethodAction {
    @Resource(name = "shippingMethodService")
    private ShippingMethodService methodService;

    private Integer id;
    private String method;
    private String message;

    public String getListMethod(HttpServletRequest request, HttpServletResponse response){
        List<ShippingMethod> methodList = methodService.list();

        return JSONArray.fromObject(methodList).toString();
    }

    /**
     * 删除配送方式的方法
     * @param request
     * @param response
     * @return
     */
    public String deleteShippingMethod(HttpServletRequest request, HttpServletResponse response) {
        Result<ShippingMethod> result = methodService.delete(id);
        return JSONUtil.toJson1(result).toString();
    }

    /**
     * 更新配送方式的方法，包括添加和修改
     * @param request
     * @param response
     * @return
     */
    public String updateShippingMethod(HttpServletRequest request, HttpServletResponse response) {
        ShippingMethod shippingMethod = new ShippingMethod();
        shippingMethod.setShippingId(id);
        shippingMethod.setMethod(method);
        shippingMethod.setMessage(message);
        Result<ShippingMethod> result = methodService.update(shippingMethod);
        return JSONUtil.toJson1(result).toString();
    }

    /**
     * 通过id获取配送方式的方法
     * @param request
     * @param response
     * @return
     */
    public String findById(HttpServletRequest request, HttpServletResponse response) {
        Result<ShippingMethod> result = methodService.findById(id);
        return JSONUtil.toJson1(result.getTarget()).toString();
    }

}
