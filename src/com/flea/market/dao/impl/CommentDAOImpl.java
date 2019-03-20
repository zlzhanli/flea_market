package com.flea.market.dao.impl;

import com.flea.market.dao.CommentDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Comment;

import java.util.List;

/**
 * @author: zhh pass
 * @time: 2019/3/7 14:33
 */

public class CommentDAOImpl extends SupBaseDAO<Comment> implements CommentDAO {
    /**
     * zhh pass
     */
    @Override
    public List<Comment> list() {
        String sql = "select * from comment";
        return list(sql,Comment.class);
    }

    @Override
    public List<Comment> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        DetachedCriteria detached = detachedCriteria.clone();
        detached.setPageing(true,begin,pageSize);
        return listByPage("select * from comment",Comment.class,detached);
    }
    /**
     * zhh pass
     */
    @Override
    public Comment findById(Integer id) {
        String sql = "select * from comment where id=?";
        return find(sql,Comment.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(Comment obj) {
        String sql = "insert into comment values(null,?,?,now(),?,?,0)";
        Integer id = execute(sql, obj.getPostId(), obj.getContent(), obj.getParentId(), obj.getCommentCustomerId());
        obj.setId(id);
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(Comment obj) {
        String sql = "delete from comment where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(Comment obj) {

    }
    /**
     * zhh pass
     */
    @Override
    public void update(Comment obj) {
        String sql = "update comment set post_id=?,content=?,time=?,parent_id=?,comment_customer_id=? where id=?";
        execute(sql,obj.getPostId(),obj.getContent(),obj.getTime(),obj.getParentId(),obj.getCommentCustomerId(),obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) as count from comment";
        return count(sql,detachedCriteria);
    }
}
