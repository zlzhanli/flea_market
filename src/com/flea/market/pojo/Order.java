package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 订单表实体类
 *
 * @author SunTao
 * @Date 2019/3/6
 */
public class Order {

    private Integer id;
    private Integer goodsId;
    private String orderSn;
    private BigDecimal orderPrice;
    private Integer shippingMethod;
    private Date orderGmtCreate;
    private Integer orderCustomerId;
    private Integer orderReceiverAddress;
    private Integer orderStatus;
    private List<OrderItems> orderItems;
    private ReceiverAddress receiverAddress;

    public ReceiverAddress getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(ReceiverAddress receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getId() {
        return id;
    }

    /**
     * 订单id
     *
     * @param id 订单id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 订单中商品的id
     *
     * @param goodsId 商品的id
     */
    @Column(name = "goods_id")
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 订单编号
     *
     * @param orderSn 订单编号
     */
    @Column(name = "order_sn")
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }


    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * 订单价格
     *
     * @param orderPrice 订单价格
     */
    @Column(name = "order_price")
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }


    public Integer getShippingMethod() {
        return shippingMethod;
    }

    /**
     * 订单配送方式
     *
     * @param shippingMethod 配送方式
     */
    @Column(name = "shipping_method")
    public void setShippingMethod(Integer shippingMethod) {
        this.shippingMethod = shippingMethod;
    }


    public Date getOrderGmtCreate() {
        return orderGmtCreate;
    }

    /**
     * 下单时间
     *
     * @param orderGmtCreate 下单时间
     */
    @Column(name = "order_gmt_create")
    public void setOrderGmtCreate(Date orderGmtCreate) {
        this.orderGmtCreate = orderGmtCreate;
    }


    public Integer getOrderCustomerId() {
        return orderCustomerId;
    }

    /**
     * 用户id
     *
     * @param orderCustomerId 用户id
     */
    @Column(name = "order_customer_id")
    public void setOrderCustomerId(Integer orderCustomerId) {
        this.orderCustomerId = orderCustomerId;
    }


    public Integer getOrderReceiverAddress() {
        return orderReceiverAddress;
    }

    /**
     * 收货地址id
     *
     * @param orderReceiverAddress 收货地址id
     */
    @Column(name = "order_receiver_address")
    public void setOrderReceiverAddress(Integer orderReceiverAddress) {
        this.orderReceiverAddress = orderReceiverAddress;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态
     * @param orderStatus 订单状态
     */
    @Column(name = "order_status")
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(goodsId, order.goodsId) &&
                Objects.equals(orderSn, order.orderSn) &&
                Objects.equals(orderPrice, order.orderPrice) &&
                Objects.equals(shippingMethod, order.shippingMethod) &&
                Objects.equals(orderGmtCreate, order.orderGmtCreate) &&
                Objects.equals(orderCustomerId, order.orderCustomerId) &&
                Objects.equals(orderReceiverAddress, order.orderReceiverAddress) &&
                Objects.equals(orderStatus, order.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, orderSn, orderPrice, shippingMethod, orderGmtCreate, orderCustomerId, orderReceiverAddress, orderStatus);
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", orderSn='" + orderSn + '\'' +
                ", orderPrice=" + orderPrice +
                ", shippingMethod='" + shippingMethod + '\'' +
                ", orderGmtCreate=" + orderGmtCreate +
                ", orderCustomerId=" + orderCustomerId +
                ", orderReceiverAddress=" + orderReceiverAddress +
                ", orderStatus=" + orderStatus +
                '}';
    }


    public Order(Integer id, Integer goodsId, String orderSn, BigDecimal orderPrice, Integer shippingMethod, Date orderGmtCreate, Integer orderCustomerId, Integer orderReceiverAddress, Integer orderStatus) {
        this.id = id;
        this.goodsId = goodsId;
        this.orderSn = orderSn;
        this.orderPrice = orderPrice;
        this.shippingMethod = shippingMethod;
        this.orderGmtCreate = orderGmtCreate;
        this.orderCustomerId = orderCustomerId;
        this.orderReceiverAddress = orderReceiverAddress;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }
}
