package com.flea.market.dao.impl;

import com.flea.market.dao.ForumDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.pojo.Forum;
import org.junit.Test;

import java.util.List;



public class ForumDAOImplTest {

    @Test
    public void list() {
        ForumDAO forumDAO = (ForumDAO) DAOFactory.createFactory().create("forumDAO");
        List<Forum> list = forumDAO.list();
        System.out.println(list);
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
        ForumDAO forumDAO = (ForumDAO) DAOFactory.createFactory().create("forumDAO");
        System.out.println(forumDAO.findById(1));
    }

    @Test
    public void save() {
        ForumDAO forumDAO = (ForumDAO) DAOFactory.createFactory().create("forumDAO");
        Forum forum = new Forum();
        forum.setForumTitle("jhdfiahd");
        forumDAO.save(forum);
    }

    @Test
    public void delete() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
        ForumDAO forumDAO = (ForumDAO) DAOFactory.createFactory().create("forumDAO");
        Forum forum = new Forum();
        forum.setForumTitle("nihao");
        forum.setId(2);
        forumDAO.update(forum);
    }

    @Test
    public void count() {
        ForumDAO forumDAO = (ForumDAO) DAOFactory.createFactory().create("forumDAO");
        System.out.println(forumDAO.count(new DetachedCriteria()));
    }
}