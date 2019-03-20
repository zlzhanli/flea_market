package com.flea.market.service.impl;

import com.flea.market.dao.GoodsStatusDAO;
import com.flea.market.entity.Result;
import com.flea.market.pojo.GoodsStatus;
import com.flea.market.service.GoodsStatusService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public class GoodsStatusServiceImpl implements GoodsStatusService {
    @Resource(name = "goodsStatusDAO")
    private GoodsStatusDAO statusDAO;

    @Override
    public List<GoodsStatus> list() {
        return statusDAO.list();
    }

    @Override
    public GoodsStatus findById(Integer id) {
        return statusDAO.findById(id);
    }

    /**
     * 更新商品状态的方法，包括增加和更新
     * @param goodsStatus status
     * @return 500 失败， 200 成功
     */
    @Override
    public Result<GoodsStatus> add(GoodsStatus goodsStatus) {
        // 判断操作的数据实体是否为空
        if (goodsStatus == null) {
            throw new NullPointerException("执行" + this.getClass().getPackage() + " 方法 save 时" + GoodsStatus.class + "为空");
        }
        Result<GoodsStatus> result = new Result<>();
        // 插入一条数据
        if (goodsStatus.getId() == null) {
            statusDAO.save(goodsStatus);
            result.setCode(200);
            result.setMsg("添加状态成功");
        } else {/////
            // 如果有id则修改当前id的数据
            GoodsStatus goodsStatusDo = statusDAO.findById(goodsStatus.getId());
            if (goodsStatus.getStatusName() != null) {
                goodsStatusDo.setStatusName(goodsStatus.getStatusName());
            }
            if (goodsStatus.getText() != null) {
                goodsStatusDo.setText(goodsStatus.getText());
            }
            // 修改数据
            statusDAO.update(goodsStatusDo);
            result.setCode(200);
            result.setMsg("修改状态成功");
        }
        return result;
    }

    @Override
    public Result<GoodsStatus> update(GoodsStatus goodsStatus) {
        return null;
    }

    /**
     * 删除状态的方法
     * @param id id
     * @return
     */
    @Override
    public Result<GoodsStatus> remove(Integer id) {
        statusDAO.delete(statusDAO.findById(id));
        Result<GoodsStatus> result = new Result<>();
        result.setCode(200);
        result.setMsg("成功删除");
        return result;
    }
}
