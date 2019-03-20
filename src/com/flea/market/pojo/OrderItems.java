package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author zl
 * @time 2019/3/11
 */
public class OrderItems {

    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    private Order order;
    private Goods goods;
    private Integer num;
    private BigDecimal price;
    private Integer orderStatus;


    public Integer getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Goods getGoods() {
        return goods;
    }

    public Integer getNum() {
        return num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    /**
     *
     * @param id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param order
     */

    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     *
     * @param goods
     */

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    /**
     *
     * @param num
     */
    @Column(name = "num")
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     *
     * @param price
     */
    @Column(name = "price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }
    @Column(name = "orders")
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }
    @Column(name = "goods")
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItems that = (OrderItems) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(goods, that.goods) &&
                Objects.equals(num, that.num) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, goods, num, price);
    }


    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", order=" + order +
                ", goods=" + goods +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}
