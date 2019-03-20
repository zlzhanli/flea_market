package com.flea.market.service;

import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author zl
 * @time 2019/3/6
 */
public interface CustomerService {

    /**
     * 搜索符合条件的记录
     *
     * @param criteria 条件对象
     * @param page 当前页数
     * @param rows 每页显示条数
     * @return
     */
    @AutoCommit
    @AutoClose
    PageBean<Customer> search(DetachedCriteria criteria, Integer page, Integer rows);


    Integer count(DetachedCriteria detachedCriteria);
    /**
     * 添加用户的方法
     *
     * @param customer
     * @return
     */
    @AutoClose
    @AutoCommit
    Result<Customer> save(Customer customer);

    /**
     * 查询loginName 是否重复
     *
     * @param loginName
     * @return
     */
    @AutoClose
    @AutoCommit
    Customer findByLoginName(String loginName);


    /**
     * 根据客户id查询客户信息
     *
     * @param id 获取客户id
     * @return 返回客户信息
     */
    @AutoClose
    @AutoCommit
    Customer findById(Integer id);

    /**
     * 修改客户的全部信息
     *
     * @param customerVo 获取到客户需修改的全部信息以及客户id
     * @return 返回业务层执行操作的结果对象
     */
    @AutoClose
    @AutoCommit
    Result<Customer> update(Customer customerVo);


    /**
     * <p>修改客户的邮箱信息</p>
     * 此方法根据用户id修改用户邮箱信息，务必保证其id不为空
     *
     * @param customerEo 获取邮箱信息以及客户id
     * @return 返回业务层执行操作的结果对象
     */
    @AutoClose
    @AutoCommit
    Result<Customer> updateEmail(Customer customerEo);


    /**
     * 根据登录名和邮箱查找用户
     * @param loginName 登录名
     * @param email 邮箱地址
     * @return 用户实体
     */
    @AutoClose
    @AutoCommit
    Customer findByLoginNameAndEmail(String loginName,String email);


    /**
     * 根据邮箱查找
     * @param email
     * @return
     */
    Customer findByEmail(String email);
    /**
     * <p>修改客户的电话信息</p>
     * 此方法根据用户id修改用户电话信息，务必保证其id不为空
     *
     * @param customerPo 获取电话信息以及客户id
     * @return 返回业务层执行操作的结果对象
     */
    @AutoClose
    @AutoCommit
    Result<Customer> updatePhone(Customer customerPo);

    /**
     * <p>修改客户的身份证信息</p>
     * 此方法根据用户id修改用户身份证信息，务必保证其id不为空
     *
     * @param customerIo 获取身份证信息以及客户id
     * @return 返回业务层执行操作的结果对象
     */
    @AutoClose
    @AutoCommit
    Result<Customer> updateIdCard(Customer customerIo);


    /**
     * <p>修改客户的头像</p>
     * 此方法根据用户id修改用户头像，务必保证其id不为空
     *
     * @param customerPt 获取头像信息以及客户id
     * @return 返回业务层执行操作的结果对象
     */
    @AutoClose
    @AutoCommit
    Result<Customer> updatePhoto(Customer customerPt);

    /**
     * <p>修改客户的昵称</p>
     * 此方法根据用户id修改用户昵称，务必保证其id不为空
     *
     * @param customerVo 数据控制层需要执行此操作的的数据
     * @return 业务层执行操作的结果对象
     */
    @AutoClose
    @AutoCommit
    Result<Customer> updateNickName(Customer customerVo);

    @AutoClose
    @AutoCommit
    Result<Customer> checkPwd(Customer customerPo);

    /**
     * <p>修改客户的密码</p>
     * 此方法根据用户id修改用户密码，务必保证其id不为空
     *
     * @param customerPo 数据控制层需要执行此操作的的数据
     * @return 业务层执行操作的结果对象
     */
    @AutoClose
    @AutoCommit
    Result<Customer> updatePwd(Customer customerPo);

    /**
     * 用户登录的方法
     *
     * @param customer
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<Customer> login(Customer customer);

    /**
     * 更新用户性别的方法
     *
     * @param customer
     * @return
     */
    @AutoCommit
    @AutoClose
    Result<Customer> updateSex(Customer customer);

    /**
     * 管理员获取用户列表的方法
     *
     * @return
     */
    @AutoCommit
    @AutoClose
    List<Customer> list();
}
