package com.flea.market.dao.impl;

import com.flea.market.dao.AdministratorDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.Administrator;
import org.junit.Test;

/**
 * @author: zhh
 * @time: 2019/3/6 15:33
 */

public class AdministratorDAOImplTest {
    AdministratorDAO administratorDAO = (AdministratorDAO) DAOFactory.createFactory().create("administratorDAO");
    Administrator admin = new Administrator();
    @Test
    public void findByLoginName(){
        Administrator administrator = administratorDAO.findByLoginName("admin");
        System.out.println(administrator);
    }

    @Test
    public void list() {
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
        System.out.println(administratorDAO.findById(1));
    }

    @Test
    public void save() {
        admin.setLoginName("kakaka");
        admin.setName("admin");
        admin.setPassword("123");
        admin.setPhone("123233123");
        administratorDAO.save(admin);
        System.out.println(admin.getId());
        System.out.println(administratorDAO.findById(admin.getId()));
    }

    @Test
    public void delete() {

        admin.setId(2);
        administratorDAO.delete(admin);

    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
    }

    @Test
    public void count() {
    }


}
