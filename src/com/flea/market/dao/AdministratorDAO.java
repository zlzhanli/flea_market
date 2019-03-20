package com.flea.market.dao;

import com.flea.market.dao.base.BaseDAO;
import com.flea.market.pojo.Administrator;

/**
 * @author: zhh
 * @time: 2019/3/6 13:34
 */

public interface AdministratorDAO extends BaseDAO<Administrator> {
    /**
     * 管理员登录
     * @param loginName 管理员所使用登录名
     * @return 管理员类
     */
    Administrator findByLoginName(String loginName);

    void updatePwd(Administrator administrator);
}