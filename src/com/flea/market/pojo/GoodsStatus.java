package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Objects;

/**
 * 商品状态实体类
 *
 * @author SunTao
 * @Date 2019/3/6
 */

public class GoodsStatus {

    private Integer id;
    private String statusName;
    private String text;


    public Integer getId() {
        return id;
    }

    /**
     * 商品状态id
     *
     * @param id 商品状态id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public String getStatusName() {
        return statusName;
    }

    /**
     * 商品状态名
     *
     * @param statusName 状态名
     */
    @Column(name = "status_name")
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }


    public String getText() {
        return text;
    }

    /**
     * 商品状态详情
     *
     * @param text 状态详情
     */
    @Column(name = "text")
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsStatus that = (GoodsStatus) o;
        return id.equals(that.id) &&
                Objects.equals(statusName, that.statusName) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusName, text);
    }

    @Override
    public String toString() {
        return "GoodsStatusAction{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
