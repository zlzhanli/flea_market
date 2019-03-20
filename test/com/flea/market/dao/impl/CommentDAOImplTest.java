package com.flea.market.dao.impl;

import com.flea.market.dao.CommentDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.pojo.Comment;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentDAOImplTest {

    @Test
    public void list() {
        CommentDAO commentDAO = (CommentDAO) DAOFactory.createFactory().create("commentDAO");
        System.out.println(commentDAO);
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
        CommentDAO commentDAO = (CommentDAO) DAOFactory.createFactory().create("commentDAO");
        System.out.println(commentDAO.findById(1));
    }

    @Test
    public void save() {
        CommentDAO commentDAO = (CommentDAO) DAOFactory.createFactory().create("commentDAO");
        Comment comment = new Comment();
        comment.setContent("isdhfushudfh");
        commentDAO.save(comment);
    }

    @Test
    public void delete() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
        CommentDAO commentDAO = (CommentDAO) DAOFactory.createFactory().create("commentDAO");
        Comment comment = new Comment();
        comment.setId(3);
        comment.setContent("nihao");
        commentDAO.update(comment);
    }

    @Test
    public void count() {
        CommentDAO commentDAO = (CommentDAO) DAOFactory.createFactory().create("commentDAO");
        System.out.println(commentDAO.count(new DetachedCriteria()));
    }
}