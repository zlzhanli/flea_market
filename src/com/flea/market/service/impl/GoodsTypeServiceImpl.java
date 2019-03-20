package com.flea.market.service.impl;

import com.flea.market.dao.GoodsDAO;
import com.flea.market.dao.GoodsTypeDAO;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Goods;
import com.flea.market.pojo.GoodsStatus;
import com.flea.market.pojo.GoodsType;
import com.flea.market.service.GoodsTypeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author karl lee
 * zhh pass
 * @Date 2019/3/8
 */
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Resource(name = "goodsTypeDAO")
    private GoodsTypeDAO goodsTypeDAO;
    /**
     * zhh pass
     */
    @Override
    public List<GoodsType> list() {
        return goodsTypeDAO.list();
    }
    /**
     * zhh pass
     */
    @Override
    public GoodsType findById(Integer id) {
        return goodsTypeDAO.findById(id);
    }

    @Override
    public Result<GoodsType> saveGoodsType(GoodsType goodsType) {
        // 判断操作的数据实体是否为空
        if (goodsType == null) {
            throw new NullPointerException("执行" + this.getClass().getPackage() + " 方法 save 时" + GoodsStatus.class + "为空");
        }
        Result<GoodsType> result = new Result<>();
        // 插入一条数据
        if (goodsType.getTypeId() == null) {
            goodsTypeDAO.save(goodsType);
            result.setCode(200);
            result.setMsg("添加状态成功");
        } else {
            // 如果有id则修改当前id的数据
            GoodsType goodsTypeDo = goodsTypeDAO.findById(goodsType.getTypeId());
            if (goodsType.getTypeName() != null) {
                goodsTypeDo.setTypeName(goodsType.getTypeName());
            }
            if (goodsType.getTypePid() != null) {
                goodsTypeDo.setTypePid(goodsType.getTypePid());
            }
            // 修改数据
            goodsTypeDAO.update(goodsTypeDo);
            result.setCode(200);
            result.setMsg("修改状态成功");
        }
        return result;

    }

    @Override
    public Result<GoodsType> deleteGoodsType(Integer goodsTypeId) {
        Result<GoodsType> result = new Result<>();
        try {
            GoodsType goodsType = goodsTypeDAO.findById(goodsTypeId);
            goodsTypeDAO.delete(goodsType);
            result.setCode(200);
            result.setMsg("删除成功");
        }catch (Exception e){
            result.setCode(500);
            result.setMsg("删除失败");
            throw  new RuntimeException("商品删除失败");
        }
        return result;
    }

    @Override
    public Result<GoodsType> updateGoodsType(GoodsType goodsType) {
        Result<GoodsType> result = new Result<>();
        try {
            goodsTypeDAO.update(goodsType);
            result.setCode(200);
            result.setMsg("修改成功");
        }catch (Exception e){
            result.setCode(500);
            result.setMsg("修改失败");
            throw  new RuntimeException("商品修改失败");
        }
        return result;
    }


}
