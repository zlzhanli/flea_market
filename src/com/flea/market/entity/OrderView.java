package com.flea.market.entity;

import com.flea.market.pojo.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/12
 */
public class OrderView implements Serializable {
    private Customer customer;
    private Order order;
    private ReceiverAddress receiverAddress;
    private ShippingMethod shippingMethod;

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ReceiverAddress getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(ReceiverAddress receiverAddress) {
        this.receiverAddress = receiverAddress;
    }




    public OrderView() {
    }
}
