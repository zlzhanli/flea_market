package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author zl
 * @time 2019/3/8
 */
public class Cart {

    private Integer id;
    private String goodsName;
    private String goodsCover;
    private BigDecimal goodsPrice;
    private Integer num;
    private Integer customerId;
    private Integer goodsId;
    private Customer customer;
    private Goods goods;

    public Integer getId() {
        return id;
    }

    /**
     * 设置购物车主键
     * @param id 购物车主键id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置购物车商品名称
     * @param goodsName 购物车商品名称
     */
    @Column(name = "goods_name")
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCover() {
        return goodsCover;
    }

    /**
     * 设置购物车商品图片
     * @param goodsCover 购物车商品图片
     */
    @Column(name = "goods_cover")
    public void setGoodsCover(String goodsCover) {
        this.goodsCover = goodsCover;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置购物车商品价格
     * @param goodsPrice 购物车商品价格
     */
    @Column(name = "goods_price")
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getNum() {
        return num;
    }

    public Integer getCustomerId() {
        return customerId;
    }
    @Column(name = "customer")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    @Column(name = "goods")
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 设置购物车商品数量
     * @param num 购物车商品数量
     */
    @Column(name = "num")
    public void setNum(Integer num) {
        this.num = num;
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * 购物车相对的客户id
     * 获取客户信息
     * @param customer 购物车对应客户
     */

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Goods getGoods() {
        return goods;
    }

    /**
     * 设置购物车相对应的购物车商品id
     * 获取商品信息
     * @param goods 购物车对应商品
     */

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(goodsName, cart.goodsName) &&
                Objects.equals(goodsCover, cart.goodsCover) &&
                Objects.equals(goodsPrice, cart.goodsPrice) &&
                Objects.equals(num, cart.num) &&
                Objects.equals(customer, cart.customer) &&
                Objects.equals(goods, cart.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsName, goodsCover, goodsPrice, num, customer, goods);
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCover='" + goodsCover + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", num=" + num +
                ", customerId=" + customerId +
                ", goodsId=" + goodsId +
                ", customer=" + customer +
                ", goods=" + goods +
                '}';
    }

    public Cart(Integer id, String goodsName, String goodsCover, BigDecimal goodsPrice, Integer num, Customer customer, Goods goods) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsCover = goodsCover;
        this.goodsPrice = goodsPrice;
        this.num = num;
        this.customer = customer;
        this.goods = goods;
    }
    public Cart(){}
}
