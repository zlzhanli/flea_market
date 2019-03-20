package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Objects;

/**
 * 管理员实体
 *
 * @author karl
 * @date 2019-03-06
 */
public class Administrator {

    private Integer id;
    private String loginName;
    private String password;
    private String phone;
    private String name;

    public Integer getId() {
        return id;
    }

    /**
     * 管理员主键id
     *
     * @param id id主键
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名称
     */
    @Column(name = "login_name")
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    /**
     * 设置管理员密码
     *
     * @param password 管理员密码
     */
    @Column(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话号码
     */
    @Column(name = "phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    /**
     * 设置管理员名
     *
     * @param name 姓名
     */
    @Column(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Administrator that = (Administrator) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(loginName, that.loginName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginName, password, phone, name);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
