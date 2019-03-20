package com.flea.market.service.impl;

import com.flea.market.dao.ShippingMethodDAO;
import com.flea.market.entity.Result;
import com.flea.market.pojo.ShippingMethod;
import com.flea.market.service.ShippingMethodService;
import com.flea.market.util.JSONUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/13
 */
public class ShippingMethodServiceImpl implements ShippingMethodService {

    @Resource(name = "shippingMethodDAO")
    private ShippingMethodDAO shippingMethodDAO;

    @Override
    public List<ShippingMethod> list() {
        List<ShippingMethod> methodList = shippingMethodDAO.list();
        System.out.println("methodList="+methodList);
        return methodList;
    }

    @Override
    public Result<ShippingMethod> delete(Integer id) {
        shippingMethodDAO.delete(shippingMethodDAO.findById(id));
        Result<ShippingMethod> result = new Result<>();
        result.setCode(200);
        result.setMsg("删除成功");
        return result;
    }

    @Override
    public Result<ShippingMethod> update(ShippingMethod shippingMethod) {
        // 判断操作的数据实体是否为空
        if (shippingMethod == null) {
            throw new NullPointerException("执行" + this.getClass().getPackage() + " 方法 save 时" + ShippingMethod.class + "为空");
        }
        Result<ShippingMethod> result = new Result<>();
        // 插入一条数据
        if (shippingMethod.getShippingId() == null) {
            shippingMethodDAO.save(shippingMethod);
            result.setCode(200);
            result.setMsg("添加状态成功");
        } else {
            // 如果有id则修改当前id的数据
            ShippingMethod shippingMethodDo = shippingMethodDAO.findById(shippingMethod.getShippingId());
            if (shippingMethod.getMethod() != null) {
                shippingMethodDo.setMethod(shippingMethod.getMethod());
            }
            if (shippingMethod.getMessage() != null) {
                shippingMethodDo.setMessage(shippingMethod.getMessage());
            }
            // 修改数据
            shippingMethodDAO.update(shippingMethodDo);
            result.setCode(200);
            result.setMsg("修改状态成功");
        }
        return result;
    }

    @Override
    public Result<ShippingMethod> findById(Integer id) {
        ShippingMethod shippingMethod = shippingMethodDAO.findById(id);
        Result<ShippingMethod> result = new Result<>();
        result.setTarget(shippingMethod);
        return result;
    }
}
