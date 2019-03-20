package com.flea.market.pojo;
import com.flea.market.util.Column;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 商品实体类
 * @author SunTao
 * @Date 2019/3/6
 *
 */
public class Goods {

    private Integer id;
    private String goodsName;
    private String goodsDetail;
    private String goodsCover;
    private String goodsFullName;
    private Integer goodsStatus;
    private Integer goodsType;
    private Integer goodsNum;
    private BigDecimal goodsPrice;
    private Date gmtCreate;
    private Date gmtModified;
    private String goodsModifyReason;
    private String goodsSn;
    private Integer goodsSource;
    private Integer shippingMethod;
    private String goodsKeywords;
    private Integer goodsPageView;
    private Integer goodsOwner;
    private GoodsStatus status;
    private GoodsType type;
    private ShippingMethod method;

    // 附属图集合
    private List<GoodsImg> list;

    public ShippingMethod getMethod() {
        return method;
    }

    public void setMethod(ShippingMethod method) {
        this.method = method;
    }

    public List<GoodsImg> getList() {
        return list;
    }

    public void setList(List<GoodsImg> list) {
        this.list = list;
    }

    public GoodsStatus getStatus() {
        return status;
    }

    public void setStatus(GoodsStatus status) {
        this.status = status;
    }

    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    /**
     * 设置商品id --- 一般使用时置为 null
     * @param id 设置商品id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     * @param goodsName 商品名称
     */
    @Column(name = "goods_name")
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    public String getGoodsDetail() {
        return goodsDetail;
    }

    /**
     * 设置商品详情
     * @param goodsDetail 商品详情
     */
    @Column(name = "goods_detail")
    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }


    public String getGoodsCover() {
        return goodsCover;
    }

    /**
     * 设置商品图片 （路径）
     * @param goodsCover 商品图片
     */
    @Column(name = "goods_cover")
    public void setGoodsCover(String goodsCover) {
        this.goodsCover = goodsCover;
    }


    public String getGoodsFullName() {
        return goodsFullName;
    }

    /**
     * 设置商品全称
     * @param goodsFullName 商品全称
     */
    @Column(name = "goods_full_name")
    public void setGoodsFullName(String goodsFullName) {
        this.goodsFullName = goodsFullName;
    }


    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    /**
     * 设置商品的状态
     * @param goodsStatus 状态
     */
    @Column(name = "goods_status")
    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }


    public Integer getGoodsType() {
        return goodsType;
    }

    /**
     * 设置商品类型
     * @param goodsType 商品类型
     */
    @Column(name = "goods_type")
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }


    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * 设置商品数量
     * @param goodsNum 商品数量
     */
    @Column(name = "goods_num")
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }


    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品价格
     * @param goodsPrice 商品价格
     */
    @Column(name = "goods_price")
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }


    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 商品创建日期
     * @param gmtCreate 商品创建日期
     */
    @Column(name = "gmt_create")
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 商品修改日期
     * @param gmtModified 修改日期
     */
    @Column(name = "gmt_modified")
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


    public String getGoodsModifyReason() {
        return goodsModifyReason;
    }

    /**
     * 商品修改的原因
     * @param goodsModifyReason 商品修改原因
     */
    @Column(name = "goods_modify_reason")
    public void setGoodsModifyReason(String goodsModifyReason) {
        this.goodsModifyReason = goodsModifyReason;
    }


    public String getGoodsSn() {
        return goodsSn;
    }

    /**
     * 商品编号
     * @param goodsSn 商品编号
     */
    @Column(name = "goods_sn")
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }


    public Integer getGoodsSource() {
        return goodsSource;
    }

    /**
     * 商品来源 1 平台自营， 2用户发布
     * @param goodsSource 商品来源
     */
    @Column(name = "goods_source")
    public void setGoodsSource(Integer goodsSource) {
        this.goodsSource = goodsSource;
    }


    public Integer getShippingMethod() {
        return shippingMethod;
    }

    /**
     * 配送方式
     * @param shippingMethod 配送方式
     */
    @Column(name = "shipping_method")
    public void setShippingMethod(Integer shippingMethod) {
        this.shippingMethod = shippingMethod;
    }


    public String getGoodsKeywords() {
        return goodsKeywords;
    }

    /**
     * 商品关键词
     * @param goodsKeywords 商品关键词
     */
    @Column(name = "goods_keywords")
    public void setGoodsKeywords(String goodsKeywords) {
        this.goodsKeywords = goodsKeywords;
    }


    public Integer getGoodsPageView() {
        return goodsPageView;
    }

    /**
     * 商品浏览量
     * @param goodsPageView 浏览量
     */
    @Column(name = "goods_page_view")
    public void setGoodsPageView(Integer goodsPageView) {
        this.goodsPageView = goodsPageView;
    }


    public Integer getGoodsOwner() {
        return goodsOwner;
    }

    /**
     * 商品拥有者
     * @param goodsOwner 商品拥有者
     */
    @Column(name = "goods_owner")
    public void setGoodsOwner(Integer goodsOwner) {
        this.goodsOwner = goodsOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) &&
                Objects.equals(goodsName, goods.goodsName) &&
                Objects.equals(goodsDetail, goods.goodsDetail) &&
                Objects.equals(goodsCover, goods.goodsCover) &&
                Objects.equals(goodsFullName, goods.goodsFullName) &&
                Objects.equals(goodsStatus, goods.goodsStatus) &&
                Objects.equals(goodsType, goods.goodsType) &&
                Objects.equals(goodsNum, goods.goodsNum) &&
                Objects.equals(goodsPrice, goods.goodsPrice) &&
                Objects.equals(gmtCreate, goods.gmtCreate) &&
                Objects.equals(gmtModified, goods.gmtModified) &&
                Objects.equals(goodsModifyReason, goods.goodsModifyReason) &&
                Objects.equals(goodsSn, goods.goodsSn) &&
                Objects.equals(goodsSource, goods.goodsSource) &&
                Objects.equals(shippingMethod, goods.shippingMethod) &&
                Objects.equals(goodsKeywords, goods.goodsKeywords) &&
                Objects.equals(goodsPageView, goods.goodsPageView) &&
                Objects.equals(goodsOwner, goods.goodsOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsName, goodsDetail, goodsCover, goodsFullName, goodsStatus, goodsType, goodsNum, goodsPrice, gmtCreate, gmtModified, goodsModifyReason, goodsSn, goodsSource, shippingMethod, goodsKeywords, goodsPageView, goodsOwner);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsCover='" + goodsCover + '\'' +
                ", goodsFullName='" + goodsFullName + '\'' +
                ", goodsStatus=" + goodsStatus +
                ", goodsType=" + goodsType +
                ", goodsNum=" + goodsNum +
                ", goodsPrice=" + goodsPrice +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", goodsModifyReason='" + goodsModifyReason + '\'' +
                ", goodsSn=" + goodsSn +
                ", goodsSource=" + goodsSource +
                ", shippingMethod=" + shippingMethod +
                ", goodsKeywords='" + goodsKeywords + '\'' +
                ", goodsPageView='" + goodsPageView + '\'' +
                ", goodsOwner=" + goodsOwner +
                '}';
    }
}
