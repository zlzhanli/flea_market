package com.flea.market.dao.impl;

import com.flea.market.dao.AdministratorDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Administrator;
import com.flea.market.pojo.Customer;

import java.util.List;

/**
 * @author: zhh  pass
 * @time: 2019/3/6 13:36
 */

public class AdministratorDAOImpl extends SupBaseDAO<Administrator> implements AdministratorDAO {
    /**
     * zhh pass
     */
    @Override
    public List<Administrator> list() {
        String sql = "select * from administrator";
        return list(sql,Administrator.class);
    }

    @Override
    public List<Administrator> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return listByPage(detachedCriteria,begin,pageSize);
    }
    /**
     * zhh pass
     */
    @Override
    public Administrator findById(Integer id) {
        String sql = "select * from administrator where id=?";
        return find(sql,Administrator.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(Administrator obj) {
        String sql = "insert into administrator values(null,?,?,?,?)";
        Integer id = execute(sql,obj.getLoginName(),obj.getPassword(),obj.getPhone(),obj.getName());
        obj.setId(id);
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(Administrator obj) {
        String sql = "delete from administrator where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(Administrator obj) {


    }
    /**
     * zhh pass
     */
    @Override
    public void update(Administrator obj) {

        String sql = "update administrator set login_name=?,password=?,phone=?,name=? where id=?";
        execute(sql,obj.getLoginName(),obj.getPassword(),obj.getPhone(),obj.getName(),obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) as count from administrator";
        return count(sql,detachedCriteria);
    }


    @Override
    public Administrator findByLoginName(String loginName) {
        String sql = "select * from administrator where login_name=?";
        return find(sql,Administrator.class,loginName);
    }

    @Override
    public void updatePwd(Administrator obj) {
            String sql = "update administrator set password = ? where id = ?";
            execute(sql, obj.getPassword(), obj.getId());
    }
}
