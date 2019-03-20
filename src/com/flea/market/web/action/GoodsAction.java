package com.flea.market.web.action;

import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Goods;
import com.flea.market.service.GoodsService;
import com.flea.market.util.Fordword;
import com.flea.market.util.JSONUtil;
import com.flea.market.util.Redirect;
import com.flea.market.util.UUIDutil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.core.util.JsonUtils;
import sun.plugin.util.UIUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author karl lee
 * @Date 2019/3/8
 */
public class GoodsAction {

    @Resource(name = "goodsService")
    private GoodsService goodsService;

    private Integer id;
    private Integer goodsStatus;
    private Integer goodsSource;
    private Integer goodsOwner;
    private Integer num;
    private String goodsSn;
    private BigDecimal goodsPrice;
    private String goodsCover;
    private String goodsModifyReason;
    private Integer goodsPageView;
    private String cover;
    private Integer shippingMethod;

    private Integer isNot;

    // 用户检索用到的条件
    private String goodsName;
    private String goodsDetail;
    private String goodsFullName;
    private String goodsKeywords;

    // 最小价格
    private BigDecimal goodPriceMin;
    // 最大价格
    private BigDecimal goodPriceMax;

    private Integer numMin;
    private Integer numMax;

    // 商品类别
    private Integer goodsType;

    /**
     * 排序
     * 0 表示不排序
     * 1 表示按价格升序
     * 2 表示按价格降序
     */
    private Integer order;

    // 分页用到的
    // 当前页数
    private Integer page;
    // 每页显示记录数
    private Integer rows;

    @Fordword
    public String findById(HttpServletRequest request, HttpServletResponse response) {
        Goods goods = goodsService.findById(id);
        request.setAttribute("goods", goods);
        return "jsp/detail.jsp";
    }

    public String find(HttpServletRequest request, HttpServletResponse response) {
        Goods goods = goodsService.findById(id);
        return JSONUtil.toJson1(goods).toString();
    }


    /**
     * 商品上架
     * @param request
     * @param response
     * @return
     */
    public String up(HttpServletRequest request, HttpServletResponse response) {
        Result<Goods> result = goodsService.up(id);
        return JSONUtil.toJson(result).toString();
    }

    /**
     *  商品下架
     * @param request
     * @param response
     * @return
     */
    public String down(HttpServletRequest request, HttpServletResponse response) {
        Result<Goods> result = goodsService.down(id);
        return JSONUtil.toJson(result).toString();
    }

    /**
     * 对商品进行搜索，
     * 支持分页查询，对商品名称，关键字，描述进行模糊查询
     * 支持对商品价格排序
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return json {total:"最大符合条件的记录数" , rows:"符合条件的商品记录"}
     */
    public String adminSearch(HttpServletRequest request, HttpServletResponse response) {
        initPage();
        Map<String, Object> map = new HashMap<>();
        DetachedCriteria criteria = new DetachedCriteria();

        // 条件价格小于 goodPriceMax
        if (goodPriceMax != null && !goodPriceMax.equals(0)) {
            criteria.add("goods_price", new DetachedCriteria.Less<>(goodPriceMax));
        }
        // 条件价格大于 goodPriceMin
        if (goodPriceMin != null && !goodPriceMin.equals(0)) {
            criteria.add("goods_price", new DetachedCriteria.Greater<>(goodPriceMin));
        }
        // 条件价格小于 goodPriceMax 并且 价格大于 goodPriceMin
        if (goodPriceMax != null && !goodPriceMax.equals(0) && goodPriceMin != null && !goodPriceMin.equals(0)) {
            criteria.add("goods_price", new DetachedCriteria.Between(goodPriceMin, goodPriceMax));
        }
        // 条件价格小于 goodPriceMax
        if (numMax != null && !numMax.equals(0)) {
            criteria.add("goods_num", new DetachedCriteria.Less<>(numMax));
        }
        // 条件价格大于 goodPriceMin
        if (numMin != null && !numMin.equals(0)) {
            criteria.add("goods_num", new DetachedCriteria.Greater<>(numMin));
        }
        // 条件价格小于 goodPriceMax 并且 价格大于 goodPriceMin
        if (numMax != null && !numMax.equals(0) && numMin != null && !numMin.equals(0)) {
            criteria.add("goods_num", new DetachedCriteria.Between(numMin, numMax));
        }

        int i = 0;
        // 条件对商品名称模糊查询
        if (goodsName != null && !goodsName.equals("")) {
            i++;
            criteria.add("goods_name", new DetachedCriteria.Like(goodsName, true, true, i != 1));
        }
        // 条件对商品全名模糊查询
        if (goodsFullName != null && !goodsFullName.equals("")) {
            i++;
            criteria.add("goods_full_name", new DetachedCriteria.Like<>(goodsFullName, true, true, i != 1));
        }
        // 条件对商品关键字模糊查询
        if (goodsKeywords != null && !goodsKeywords.equals("")) {
            i++;
            criteria.add("goods_keywords", new DetachedCriteria.Like<>(goodsKeywords, true, true, i != 1));
        }
        // 条件对商品描述模糊查询
        if (goodsDetail != null && !goodsDetail.equals("")) {
            i++;
            criteria.add("goods_detail", new DetachedCriteria.Like<>(goodsDetail, true, true, i != 1));
        }

        // 条件对商品类型查询
        if (goodsType != null && !goodsType.equals(0)) {
            criteria.add("goods_type", new DetachedCriteria.FactorImp<Integer>(goodsType));
        }

        if (goodsStatus != null && !goodsStatus.equals(0)) {
            criteria.add("goods_status", new DetachedCriteria.FactorImp(goodsStatus));
            if (isNot != null && isNot == 1) {
                criteria.add("goods_status", new DetachedCriteria.NoFactorImp(goodsStatus));
            }
        }

        // 执行搜索
        PageBean<Goods> pageBean = goodsService.search(criteria, page, rows);

        // 按价格升序排序
        if (order != null && !order.equals(0) && order == 1) {
            pageBean.getList().sort(Comparator.comparing(Goods::getGoodsPrice));
        }

        // 按价格降序排序
        if (order != null && !order.equals(0) && order == 2) {
            Collections.reverse(pageBean.getList());
        }
        // 查询到的商品记录
        map.put("rows", pageBean.getList());
        // 返回页面符合条件的总记录数
        map.put("total", pageBean.getTotalCount());
        map.put("current", pageBean.getCurrPage());

        return JSONUtil.toJson1(map).toString();
    }

    /**
     * 对商品进行搜索，
     * 支持分页查询，对商品名称，关键字，描述进行模糊查询
     * 支持对商品价格排序
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return json {total:"最大符合条件的记录数" , rows:"符合条件的商品记录"}
     */
    public String search(HttpServletRequest request, HttpServletResponse response) {
        initPage();
        Map<String, Object> map = new HashMap<>();
        DetachedCriteria criteria = new DetachedCriteria();



        // 条件价格小于 goodPriceMax
        if (goodPriceMax != null && !goodPriceMax.equals(0)) {
            criteria.add("goods_price", new DetachedCriteria.Less<>(goodPriceMax));
        }
        // 条件价格大于 goodPriceMin
        if (goodPriceMin != null && !goodPriceMin.equals(0)) {
            criteria.add("goods_price", new DetachedCriteria.Greater<>(goodPriceMin));
        }
        // 条件价格小于 goodPriceMax 并且 价格大于 goodPriceMin
        if (goodPriceMax != null && !goodPriceMax.equals(0) && goodPriceMin != null && !goodPriceMin.equals(0)) {
            criteria.add("goods_price", new DetachedCriteria.Between(goodPriceMin, goodPriceMax));
        }
        // 条件价格小于 goodPriceMax
        if (numMax != null && !numMax.equals(0)) {
            criteria.add("goods_num", new DetachedCriteria.Less<>(numMax));
        }
        // 条件价格大于 goodPriceMin
        if (numMin != null && !numMin.equals(0)) {
            criteria.add("goods_num", new DetachedCriteria.Greater<>(numMin));
        }
        // 条件价格小于 goodPriceMax 并且 价格大于 goodPriceMin
        if (numMax != null && !numMax.equals(0) && numMin != null && !numMin.equals(0)) {
            criteria.add("goods_num", new DetachedCriteria.Between(numMin, numMax));
        }

        int i = 0;
        // 条件对商品名称模糊查询
        if (goodsName != null && !goodsName.equals("")) {
            i++;
            criteria.add("goods_name", new DetachedCriteria.Like(goodsName, true, true, i != 1));
        }
        // 条件对商品全名模糊查询
        if (goodsFullName != null && !goodsFullName.equals("")) {
            i++;
            criteria.add("goods_full_name", new DetachedCriteria.Like<>(goodsFullName, true, true, i != 1));
        }
        // 条件对商品关键字模糊查询
        if (goodsKeywords != null && !goodsKeywords.equals("")) {
            i++;
            criteria.add("goods_keywords", new DetachedCriteria.Like<>(goodsKeywords, true, true, i != 1));
        }
        // 条件对商品描述模糊查询
        if (goodsDetail != null && !goodsDetail.equals("")) {
            i++;
            criteria.add("goods_detail", new DetachedCriteria.Like<>(goodsDetail, true, true, i != 1));
        }

        // 条件对商品类型查询
        if (goodsType != null && !goodsType.equals(0)) {
            criteria.add("goods_type", new DetachedCriteria.FactorImp<Integer>(goodsType));
        }

        // 保证商品已上架
        criteria.add("goods_status", new DetachedCriteria.FactorImp(3));

        // 执行搜索
        PageBean<Goods> pageBean = goodsService.search(criteria, page, rows);

        // 按价格升序排序
        if (order != null && !order.equals(0) && order == 1) {
            pageBean.getList().sort(Comparator.comparing(Goods::getGoodsPrice));
        }

        // 按价格降序排序
        if (order != null && !order.equals(0) && order == 2) {
            Collections.reverse(pageBean.getList());
        }
        // 查询到的商品记录
        map.put("rows", pageBean.getList());
        // 返回页面符合条件的总记录数
        map.put("total", pageBean.getTotalCount());
        map.put("current", pageBean.getCurrPage());

        return JSONUtil.toJson1(map).toString();
    }

    /**
     * 管理员添加商品
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public String adminSave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Goods goods = create();

        // 商品来源自营
        goods.setGoodsSource(1);
        // 商品拥有者，平台方
        goods.setGoodsOwner(1);
        // 设置商品编号
        goods.setGoodsSn(UUIDutil.getUUID());
        // 设置修改日期
        goods.setGmtModified(new Date());
        // 设置修改原因
        goods.setGoodsModifyReason("添加新商品");
        // 初始化浏览量
        goods.setGoodsPageView(0);


        Result<Goods> result = goodsService.save(goods);
        return JSONUtil.toJson1(result).toString();
    }

    /**
     * 管理员修改商品
     *
     * @param request
     * @param response
     * @return
     */
    public String updateGoods(HttpServletRequest request, HttpServletResponse response) {
        Goods goods = create();
        goods.setGoodsCover(goodsCover);
        Result<Goods> result = goodsService.save(goods);
        return JSONUtil.toJson1(result).toString();
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) {
        Goods goods = create();
        Result<Goods> result = goodsService.delete(goods);
        return JSONUtil.toJson1(result).toString();
    }

    // 初始化页数
    private void initPage() {
        if (rows == null) {
            rows = 10;
        }
        if (page == null) {
            page = 1;
        }
    }

    /**
     * 根据前端数据创建goods
     *
     * @return
     */
    private Goods create() {
        Goods goods = new Goods();
        if (id != null && !id.equals(0)) {
            goods.setId(id);
        }
        if (goodsName != null && !goodsName.equals("")) {
            goods.setGoodsName(goodsName);
        }
        if (goodsFullName != null && !goodsFullName.equals("")) {
            goods.setGoodsFullName(goodsFullName);
        }
        if (goodsDetail != null && !goodsDetail.equals("")) {
            goods.setGoodsDetail(goodsDetail);
        }
        if (goodsPageView != null && !goodsPageView.equals("")) {
            goods.setGoodsPageView(goodsPageView);
        }
        if (goodsKeywords != null && !goodsKeywords.equals("")) {
            goods.setGoodsKeywords(goodsKeywords);
        }
        if (cover != null && !cover.equals("")) {
            goods.setGoodsCover(cover);
        }
        if (goodsSn != null && !goodsSn.equals("")) {
            goods.setGoodsSn(goodsSn);
        }

        if (shippingMethod != null && !shippingMethod.equals(0)) {
            goods.setShippingMethod(shippingMethod);
        }
        if (goodsStatus != null && !goodsStatus.equals(0)) {
            goods.setGoodsStatus(goodsStatus);
        }
        if (goodsType != null && !goodsType.equals(0)) {
            goods.setGoodsType(goodsType);
        }
        if (goodsOwner != null && !goodsOwner.equals(0)) {
            goods.setGoodsOwner(goodsOwner);
        }
        if (goodsSource != null && !goodsSource.equals(0)) {
            goods.setGoodsSource(goodsSource);
        }
        if (num != null && !num.equals(0)) {
            goods.setGoodsNum(num);
        }
        if (goodsPrice != null) {
            goods.setGoodsPrice(goodsPrice);
        }
        return goods;
    }

    private boolean isOr() {
        if (countSearchNull() > 1) {
            return true;
        }
        return false;
    }

    private Integer countSearchNull() {
        Integer count = 0;

        if (goodsName == null || "".equals(goodsName)) {
                count++;
        }
        if (goodsFullName == null || "".equals(goodsFullName)) {
            count++;
        }
        if (goodsKeywords == null || "".equals(goodsKeywords)) {
            count++;
        }
        if (goodsDetail == null || "".equals(goodsDetail)) {
            count++;
        }

        return count;
    }
    @Override
    public String toString() {
        return "GoodsAction{" +
                "goodsService=" + goodsService +
                ", id=" + id +
                ", goodsStatus=" + goodsStatus +
                ", goodsSource=" + goodsSource +
                ", goodsOwner=" + goodsOwner +
                ", num=" + num +
                ", goodsSn=" + goodsSn +
                ", goodsPrice=" + goodsPrice +
                ", goodsModifyReason='" + goodsModifyReason + '\'' +
                ", goodsPageView='" + goodsPageView + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsFullName='" + goodsFullName + '\'' +
                ", goodsKeywords='" + goodsKeywords + '\'' +
                ", goodPriceMin=" + goodPriceMin +
                ", goodPriceMax=" + goodPriceMax +
                ", goodsType=" + goodsType +
                ", order=" + order +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}
