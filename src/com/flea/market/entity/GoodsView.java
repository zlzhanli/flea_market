package com.flea.market.entity;

import com.flea.market.pojo.Customer;
import com.flea.market.pojo.Goods;
import com.flea.market.pojo.GoodsStatus;
import com.flea.market.pojo.GoodsType;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author SunTao
 * @Date 2019/3/12
 */
public class GoodsView implements Serializable {
    private Goods goods;
    private String  goodsType;
    private String goodsStatus;
    private String customerName;


    public GoodsView() {
    }

    @Override
    public String toString() {
        return "GoodsView{" +
                "goods=" + goods +
                ", goodsType='" + goodsType + '\'' +
                ", goodsStatus='" + goodsStatus + '\'' +
                ", customerName='" + customerName + '\'' +
                "}\n";
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsView goodsView = (GoodsView) o;
        return Objects.equals(goods, goodsView.goods) &&
                Objects.equals(goodsType, goodsView.goodsType) &&
                Objects.equals(goodsStatus, goodsView.goodsStatus) &&
                Objects.equals(customerName, goodsView.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goods, goodsType, goodsStatus, customerName);
    }
}
