package com.flea.market.dao;

import com.flea.market.dao.base.BaseDAO;
import com.flea.market.pojo.Customer;

/**
 * @author karl lee
 * @Date 2019/3/6
 */
public interface CustomerDAO extends BaseDAO<Customer> {

    /**
     *  <p>客户模糊登录使用</p>
     *  客户登录时可根据其输入的是登录名或者邮箱或者电话进行登录
     *
     * @param loginNme 登录名
     * @param email 邮箱
     * @param phone 电话号码
     * @return 符合条件的客户
     */
    Customer findByLoginNameOrPhoneOrEmail(String loginNme,String email,String phone);

    /**
     * 根据姓名和邮箱获取用户实体的方法
     * @param loginName
     * @param eamil
     * @return
     */
    Customer findByLoginNameAndEmail(String loginName,String eamil);

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    Customer findByEmail(String email);
    
    /**
     * 检查数据库中是否存在此条login_name信息
     * @param loginName
     * @return
     */
    Customer findByLoginName(String loginName);
    /**
     * 客户在修改头像时使用
     * 根据客户id进行修改
     * @param obj 获取客户id以及新的photo地址
     */
    void updatePhoto(Customer obj);

    /**
     * 修改密码
     * 客户输入旧密码与数据库原密码一致，才能提交新的密码
     * 新密码前台页面两次验证一致
     * 提交后，发送邮箱验证，验证码一致，修改密码
     * @param obj
     */
    void updatePwd(Customer obj);

    /**
     * 单独修改用户的昵称
     * 方法根据id修改，务必保证其不为空
     * 此方法与update操作类似，需要保证修改的数据不能为空，建议使用者先持久化数据（先查询）再执行此操作
     * 此方法减少数据库压力，单独修改必要数据，不进行大范围修改
     * @param customer 需要修改的用户 只修改用户昵称nickName字段
     */
    void updateNickName(Customer customer);



}
