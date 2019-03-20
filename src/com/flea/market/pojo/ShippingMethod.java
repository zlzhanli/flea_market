package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Objects;

/**
 * @author zl
 * @time 2019/3/13
 */
public class ShippingMethod {

    private Integer shippingId;
    private String method;
    private String message;

    public Integer getShippingId() {
        return shippingId;
    }

    @Column(name = "id")
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public String getMethod() {
        return method;
    }

    @Column(name = "method")
    public void setMethod(String method) {
        this.method = method;
    }

    public String getMessage() {
        return message;
    }

    @Column(name = "message")
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShippingMethod that = (ShippingMethod) o;
        return Objects.equals(shippingId, that.shippingId) &&
                Objects.equals(method, that.method) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shippingId, method, message);
    }


    @Override
    public String toString() {
        return "ShippingMethod{" +
                "shippingId=" + shippingId +
                ", method='" + method + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
