
package com.flea.market.service.impl;

import com.flea.market.dao.*;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Goods;
import com.flea.market.pojo.GoodsImg;
import com.flea.market.pojo.GoodsStatus;
import com.flea.market.pojo.GoodsType;
import com.flea.market.service.GoodsService;
import jdk.nashorn.internal.ir.annotations.Reference;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/8
 */
public class GoodsServiceImpl implements GoodsService {
    @Resource(name = "goodsDAO")
    private GoodsDAO goodsDAO;

    @Resource(name = "goodsImgDAO")
    private GoodsImgDAO goodsImgDAO;

    @Resource(name = "goodsTypeDAO")
    private GoodsTypeDAO goodsTypeDAO;

    @Resource(name = "goodsStatusDAO")
    private GoodsStatusDAO goodsStatusDAO;

    @Resource(name = "shippingMethodDAO")
    private ShippingMethodDAO methodDAO;

    @Override
    public PageBean<Goods> search(DetachedCriteria criteria, Integer page, Integer rows) {
        PageBean<Goods> pageBean = new PageBean<>();
        // 设置当前页数:
        Integer currPage = page;
        Integer pageSize = rows;
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数：
        pageBean.setPageSize(pageSize);
        // 设置总记录数:
        Integer totalCount = goodsDAO.count(criteria);
        pageBean.setTotalCount(totalCount);
        // 设置总页数：
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 设置每页显示数据的集合:
        Integer begin = (currPage - 1) * pageSize;

        List<Goods> list = goodsDAO.listByPage(criteria, begin, pageSize);

        for (Goods goods : list) {
            if (goods.getGoodsStatus() != null) {
                goods.setStatus(goodsStatusDAO.findById(goods.getGoodsStatus()));
            }
            if (goods.getGoodsType() != null) {
                goods.setType(goodsTypeDAO.findById(goods.getGoodsType()));
            }
            if (goods.getShippingMethod() != null) {
                goods.setMethod(methodDAO.findById(goods.getShippingMethod()));
            }
        }
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public List<Goods> list() {
        List<Goods> list_goods = goodsDAO.list();
        return list_goods;
    }

    @Override
    public Goods findById(Integer id) {
        Goods goods = goodsDAO.findById(id);
        List<GoodsImg> list = goodsImgDAO.listByGoodsId(id);
        goods.setList(list);
        if (goods.getGoodsStatus() != null) {
            goods.setStatus(goodsStatusDAO.findById(goods.getGoodsStatus()));
        }
        if (goods.getGoodsType() != null) {
            goods.setType(goodsTypeDAO.findById(goods.getGoodsType()));
        }
        return goods;
    }
    @Override
    public Result<Goods> save(Goods goods) {

        // 判断操作的数据实体是否为空
        if (goods == null) {
            throw new NullPointerException("执行" + this.getClass().getPackage() + " 方法 save 时" + Goods.class + "为空");
        }
        Result<Goods> result = new Result<>();
        // 插入一条数据
        if (goods.getId() == null) {
            goodsDAO.save(goods);
            if (goods.getId() == null) {
                result.setCode(500);
                result.setMsg("新插入一条数据失败");
                return result;
            }
            result.setCode(200);
            result.setMsg("新插入数据成功");
            result.setTarget(goodsDAO.findById(goods.getId()));
        } else {
            // 获取数据库中的记录
            Goods goodsDo = goodsDAO.findById(goods.getId());

            if (goods.getGoodsModifyReason() != null) {
                throw new RuntimeException("异常，修改此数据必须有修改原因GoodsModifyReason 字段");
            }

            // 修改非空的数据
            if (goods.getGoodsName() != null) {
                goodsDo.setGoodsName(goods.getGoodsName());
            }
            if (goods.getGoodsFullName() != null) {
                goodsDo.setGoodsFullName(goods.getGoodsFullName());
            }
            if (goods.getGoodsDetail() != null) {
                goodsDo.setGoodsDetail(goods.getGoodsDetail());
            }
            if (goods.getGoodsCover() != null) {
                goodsDo.setGoodsCover(goods.getGoodsCover());
            }
            if (goods.getGoodsStatus() != null) {
                goodsDo.setGoodsStatus(goods.getGoodsStatus());
            }
            if (goods.getGoodsType() != null) {
                goodsDo.setGoodsType(goods.getGoodsType());
            }
            if (goods.getGoodsNum() != null) {
                goodsDo.setGoodsNum(goods.getGoodsNum());
            }
            if (goods.getGoodsPrice() != null) {
                goodsDo.setGoodsPrice(goods.getGoodsPrice());
            }
            if (goods.getGoodsPageView() != null) {
                goodsDo.setGoodsPageView(goods.getGoodsPageView());
            }
            if (goods.getGoodsSn() != null) {
                goodsDo.setGoodsSn(goods.getGoodsSn());
            }
            if (goods.getGoodsSource() != null) {
                goodsDo.setGoodsSource(goods.getGoodsSource());
            }
            if (goods.getGoodsCover() != null) {
                goodsDo.setGoodsCover(goods.getGoodsCover());
            }
            if (goods.getShippingMethod() != null) {
                goodsDo.setShippingMethod(goods.getShippingMethod());
            }
            if (goods.getGoodsKeywords() != null) {
                goodsDo.setGoodsCover(goods.getGoodsCover());
            }
            if (goods.getGoodsOwner() != null) {
                goodsDo.setGoodsOwner(goods.getGoodsOwner());
            }
            // 修改数据
            goodsDAO.update(goodsDo);
            result.setCode(200);
            result.setMsg("修改数据记录成功");
        }

        return result;
    }

    @Override
    public Result<Goods> delete(Goods goods) {
        Result<Goods> result = new Result<>();
        try {
            goodsDAO.remove(goods);
            result.setCode(200);
            result.setMsg("删除成功");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("删除失败");
            throw new RuntimeException("商品删除失败");
        }
        return result;
    }

    @Override
    public Result<Goods> up(Integer id) {
        return updateState(id, 3, "上架");
    }

    @Override
    public Result<Goods> down(Integer id) {
        return updateState(id, 4, "下架");
    }

    private Result<Goods> updateState(Integer id,Integer state, String mess) {
        Result<Goods> result = new Result<>();
        try {
            Goods goodsDo = goodsDAO.findById(id);
            goodsDo.setGoodsStatus(state);
            goodsDAO.update(goodsDo);
            result.setCode(200);
            result.setMsg(mess + "成功");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg(mess + "失败");
            throw new RuntimeException("商品" + mess + "失败");
        }
        return result;
    }

}

