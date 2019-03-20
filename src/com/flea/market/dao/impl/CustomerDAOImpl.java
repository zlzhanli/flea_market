package com.flea.market.dao.impl;

import com.flea.market.dao.CustomerDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Customer;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/6
 */

public class CustomerDAOImpl extends SupBaseDAO<Customer> implements CustomerDAO {
    /**
     * zhh pass
     */
    @Override
    public List<Customer> list() {
        String sql = "select * from customer";
        return list(sql, Customer.class);
    }

    @Override
    public List<Customer> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria=detachedCriteria.clone();
        detachedCriteria.setPageing(true,begin,pageSize);
        return listByPage("select * from customer ",Customer.class,detachedCriteria);
    }
    /**
     * zhh pass
     */
    @Override
    public Customer findById(Integer id) {
        String sql = "select * from customer where id = ?";
        return find(sql, Customer.class, id);
    }

    /**
     * 检查数据库中是否存在此条login_name信息
     *
     * @param loginName
     * @return
     */

    @Override
    public Customer findByLoginName(String loginName) {
        String sql = "select * from customer where login_name = ?";
        return find(sql, Customer.class, loginName);
    }

    /**
     * 用于注册时添加用户信息
     * @param obj target
     */
    /**
     * zhh pass
     */
    @Override
    public void save(Customer obj) {
        String sql = "insert into customer set gmt_create = now(),password = ?,nick_name = ?,login_name = ?,user_balance=?,user_status=?,photo=?";
        Integer id = execute(sql, obj.getPassword(), obj.getNickName(), obj.getLoginName(), obj.getUserBalance(),obj.getUserStatus(),obj.getPhoto());
        obj.setId(id);

    }
    /**
     * zhh pass
     */
    @Override
    public void delete(Customer obj) {
        String sql = "delete from customer where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(Customer obj) {

    }
    /**
     * zhh pass
     */
    @Override
    public void update(Customer obj) {
        String sql = "update customer set gmt_create = ?, gmt_create = ?, user_balance = ?, user_status = ?, " +
                "birthday = ?, province = ?, city = ?, area = ?, sex = ?, email = ?, phone = ?, id_card = ?, " +
                "password = ?, nick_name = ?, login_name = ?, real_name = ?, photo = ? where id = ?";
        execute(sql, obj.getGmtCreate(), obj.getGmtModified(), obj.getUserBalance(), obj.getUserStatus(),
                obj.getBirthday(), obj.getProvince(), obj.getCity(), obj.getArea(), obj.getSex(),
                obj.getEmail(), obj.getPhone(), obj.getIdCard(), obj.getPassword(), obj.getNickName(),
                obj.getLoginName(), obj.getRealName(), obj.getPhoto(), obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) as count from customer ";
        return count(sql,detachedCriteria);
    }

    @Override
    public Customer findByLoginNameOrPhoneOrEmail(String loginName, String email, String phone) {
        String sql = "select * from customer where login_name = ? or phone = ? or email = ?";
        return find(sql, Customer.class, loginName, phone, email);
    }


    public Customer findByLoginNameAndEmail(String loginName, String eamil) {
        String sql = "select * from customer where login_name = ? && email = ?";
        return find(sql,Customer.class,loginName,eamil);
    }


    public Customer findByEmail(String email) {
        String sql = "select * from customer where email = ?";
        return find(sql,Customer.class,email);
    }

    @Override
    public void updatePhoto(Customer obj) {
        String sql = "update customer set photo = ? where id = ?";
        execute(sql, obj.getPhoto(), obj.getId());
    }


    @Override
    public void updatePwd(Customer obj) {
        String sql = "update customer set password = ? where id = ?";
        execute(sql, obj.getPassword(), obj.getId());
    }

    @Override
    public void updateNickName(Customer customer) {
        execute("update customer set nick_name = ? where id = ?", customer.getNickName(), customer.getId());
    }

}
