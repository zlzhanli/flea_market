package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Objects;

/**
 * 客户状态实体
 *
 * @author karl
 * @date 2019-03-06
 */
public class CustomerStatus {

    private Integer id;
    private String customerStatusTitle;
    private String customerStatusDesc;


    public Integer getId() {
        return id;
    }

    /**
     * 设置用户状态主键
     * @param id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public String getCustomerStatusTitle() {
        return customerStatusTitle;
    }
    /**
     * 设置用户状态名称
     * @param customerStatusTitle 用户状态名称
     */
    @Column(name = "customer_status_title")
    public void setCustomerStatusTitle(String customerStatusTitle) {
        this.customerStatusTitle = customerStatusTitle;
    }


    public String getCustomerStatusDesc() {
        return customerStatusDesc;
    }
    /**
     * 设置用户状态描述
     * @param customerStatusDesc 用户状态描述
     */
    @Column(name = "customer_status_desc")
    public void setCustomerStatusDesc(String customerStatusDesc) {
        this.customerStatusDesc = customerStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerStatus that = (CustomerStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customerStatusTitle, that.customerStatusTitle) &&
                Objects.equals(customerStatusDesc, that.customerStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerStatusTitle, customerStatusDesc);
    }

    @Override
    public String toString() {
        return "CustomerStatus{" +
                "id=" + id +
                ", customerStatusTitle='" + customerStatusTitle + '\'' +
                ", customerStatusDesc='" + customerStatusDesc + '\'' +
                '}';
    }
}
