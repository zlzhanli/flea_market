package com.flea.market.entity;

import java.io.Serializable;

/**
 * @author LiuTianyou
 * @date 2019/3/8
 */

public class CardNumberInfo implements Serializable {
    private  String area;
    private  String birthday;
    private  String sex;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "CardNumberInfo{" +
                "area='" + area + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
