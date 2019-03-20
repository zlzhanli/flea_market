package com.flea.market.pojo;

import com.flea.market.util.Column;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Objects;

/**
 * 收货人地址表实体类
 *
 * @author SunTao
 * @Date 2019/3/6
 */
public class ReceiverAddress {

    private Integer id;
    private Integer customerId;
    private String receiverName;
    private String receiverSex;
    private String receiverPhone;
    private Integer province;
    private Integer city;
    private Integer area;
    private String detial;
    private Integer isDefault;
    private String addr;
    private String addressLabel;
    private Long status;

    public String getAddr() {
        return addr;
    }

    public Long getStatus() {
        return status;
    }


    @Column(name="status")
    public void setStatus(Long status) {
        this.status = status;
    }

    @Column(name="addr")
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * 收货人地址表id
     * @param id 收货人地址表id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId(){
        return id;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 用户id
     * @param customerId 用户id
     */
    @Column(name = "customer_id")
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 收货人姓名
     * @param receiverName 收货人姓名
     */
    @Column(name = "receiver_name")
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }


    public String getReceiverSex() {
        return receiverSex;
    }

    /**
     * 收货人性别
     * @param receiverSex 收货人性别
     */
    @Column(name = "receiver_sex")
    public void setReceiverSex(String receiverSex) {
        this.receiverSex = receiverSex;
    }


    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 收货人电话
     * @param receiverPhone 收货人电话
     */
    @Column(name = "receiver_phone")
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }


    public Integer getProvince() {
        return province;
    }

    /**
     * 收货人所在省份
     * @param province 省份
     */
    @Column(name = "province")
    public void setProvince(Integer province) {
        this.province = province;
    }


    public Integer getCity() {
        return city;
    }

    /**
     * 收货人所在城市
     * @param city 城市
     */
    @Column(name = "city")
    public void setCity(Integer city) {
        this.city = city;
    }


    public Integer getArea() {
        return area;
    }

    /**
     * 收货人所在区
     * @param area 区
     */
    @Column(name = "area")
    public void setArea(Integer area) {
        this.area = area;
    }


    public String getDetial() {
        return detial;
    }

    /**
     * 收货人详细地址
     * @param detial 详细地址
     */
    @Column(name = "detail")
    public void setDetial(String detial) {
        this.detial = detial;
    }


    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 是否是默认的收货地址
     * @param isDefault 是否是默认的收货地址
     */
    @Column(name = "is_default")
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }


    public String getAddressLabel() {
        return addressLabel;
    }

    /**
     * 地址标签
     * @param addressLabel 地址标签
     */
    @Column(name = "address_label")
    public void setAddressLabel(String addressLabel) {
        this.addressLabel = addressLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReceiverAddress that = (ReceiverAddress) o;
        return
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(receiverName, that.receiverName) &&
                Objects.equals(receiverPhone, that.receiverPhone) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(area, that.area) &&
                Objects.equals(detial, that.detial) &&
                Objects.equals(addr, that.addr) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, receiverName, receiverSex, receiverPhone, province, city, area, detial, isDefault, addr, addressLabel);
    }

    @Override
    public String toString() {
        return "ReceiverAddress{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", receiverName='" + receiverName + '\'' +
                ", receiverSex='" + receiverSex + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", area=" + area +
                ", detial='" + detial + '\'' +
                ", isDefault=" + isDefault +
                ", addr='" + addr + '\'' +
                ", addressLabel='" + addressLabel + '\'' +
                ", status=" + status +
                '}';
    }
}
