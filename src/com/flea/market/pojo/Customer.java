package com.flea.market.pojo;


import com.flea.market.util.Column;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Objects;

/**
 * 客户实体类
 *
 * @author karl
 * @date 2019-03-06
 */
public class Customer {

    private Integer id;
    private Date gmtCreate;
    private Date gmtModified;
    private BigDecimal userBalance;
    private Integer userStatus;
    private Date birthday;
    private String province;
    private String city;
    private String area;
    private String sex;
    private String email;
    private String phone;
    private String idCard;
    private String password;
    private String nickName;
    private String loginName;
    private String realName;
    private String photo;


    public Integer getId() {
        return id;
    }

    /**
     * 设置客户主键id
     *
     * @param id 客户主键id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    @Column(name = "gmt_create")
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    @Column(name = "gmt_modified")
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


    public BigDecimal getUserBalance() {
        return userBalance;
    }

    /**
     * 设置客户余额
     *
     * @param userBalance 客户余额
     */
    @Column(name = "user_balance")
    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }


    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置状态
     *
     *
     * @param userStatus 客户状态
     */
    @Column(name = "user_status")
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }


    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置客户生日
     *
     * @param birthday 客户生日
     */
    @Column(name = "birthday")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String getProvince() {
        return province;
    }

    /***
     * 设置省
     * @param province 省
     */
    @Column(name = "province")
    public void setProvince(String province) {
        this.province = province;
    }


    public String getCity() {
        return city;
    }

    /***
     * 设置市
     * @param city 市
     */
    @Column(name = "city")
    public void setCity(String city) {
        this.city = city;
    }


    public String getArea() {
        return area;
    }

    /***
     * 设置区
     * @param area 区
     */
    @Column(name = "area")
    public void setArea(String area) {
        this.area = area;
    }


    public String getSex() {
        return sex;
    }

    /***
     * 设置性别
     * @param sex 性别
     */
    @Column(name = "sex")
    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getEmail() {
        return email;
    }

    /***
     * 设置邮箱
     * @param email 邮箱
     */
    @Column(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    /***
     * 设置电话
     * @param phone 电话
     */
    @Column(name = "phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getIdCard() {
        return idCard;
    }

    /***
     * 设置身份证
     * @param idCard 身份证
     */
    @Column(name = "id_card")
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }


    public String getPassword() {
        return password;
    }

    /***
     * 设置密码
     * @param password 密码
     */
    @Column(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }


    public String getNickName() {
        return nickName;
    }

    /***
     * 设置昵称
     * @param nickName 昵称
     */
    @Column(name = "nick_name")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名称
     *
     * @param loginName 登录名
     */
    @Column(name = "login_name")
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     * @param realName 真实姓名
     */
    @Column(name = "real_name")
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    /**
     * 设置头像
     * @param photo 头像
     */
    @Column(name = "photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(gmtCreate, customer.gmtCreate) &&
                Objects.equals(gmtModified, customer.gmtModified) &&
                Objects.equals(userBalance, customer.userBalance) &&
                Objects.equals(userStatus, customer.userStatus) &&
                Objects.equals(birthday, customer.birthday) &&
                Objects.equals(province, customer.province) &&
                Objects.equals(city, customer.city) &&
                Objects.equals(area, customer.area) &&
                Objects.equals(sex, customer.sex) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(idCard, customer.idCard) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(nickName, customer.nickName) &&
                Objects.equals(loginName, customer.loginName) &&
                Objects.equals(realName, customer.realName) &&
                Objects.equals(photo, customer.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gmtCreate, gmtModified, userBalance, userStatus, birthday, province, city, area, sex, email, phone, idCard, password, nickName, loginName, realName, photo);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", userBalance=" + userBalance +
                ", userStatus=" + userStatus +
                ", birthday=" + birthday +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", realName='" + realName + '\'' +
                ", photo='" + photo + '\'' +
                "}\n";
    }
}
