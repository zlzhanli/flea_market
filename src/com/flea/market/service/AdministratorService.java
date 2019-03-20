package com.flea.market.service;


import com.flea.market.entity.Result;
import com.flea.market.pojo.Administrator;
import com.flea.market.pojo.Goods;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

/**
 * @author: zhh
 * @time: 2019/3/6 13:53
 */
public interface AdministratorService {
    /**
     * 根据用户名查找用户实体
     *
     * 此方法返回结果集为 code msg target
     * code ：200：正常
     *       500  密码错误
     *       505 系统异常
     *       404 找不到用户，用户不存在
     * target 登录成功后返回 符合条件的实体对象
     * msg 方法执行返回的消息
     *
     * @param administratorVo 用户输入的用户名和密码等信息组成的实体
     * @return 查找到的用户实体
     */
    @AutoCommit
    @AutoClose
    Result<Administrator> login(Administrator administratorVo);

    @AutoCommit
    @AutoClose
    Result<Administrator> checkPwd(Administrator administrator);

    @AutoCommit
    @AutoClose
    Goods findGood(Integer id);


    @AutoCommit
    @AutoClose
    Administrator findById(Integer id);

    @AutoCommit
    @AutoClose
    Result<Administrator> updatePwd(Administrator administrator);
}
