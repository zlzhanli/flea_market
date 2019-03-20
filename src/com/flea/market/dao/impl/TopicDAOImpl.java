package com.flea.market.dao.impl;

import com.flea.market.dao.TopicDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Topic;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/6
 */
public class TopicDAOImpl extends SupBaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> list() {
        return list("select * from  topic", Topic.class);
    }

    @Override
    public List<Topic> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return listByPage(detachedCriteria,begin,pageSize);
    }

    @Override
    public Topic findById(Integer id) {
        String sql= "select * from topic where id = ?";
        return find(sql,Topic.class,id);
    }

    @Override
    public void save(Topic obj) {

        String sql = " insert into topic values(null,?)";
        execute(sql,obj.getTopicTitle());
    }

    @Override
    public void delete(Topic obj) {
        String sql = "delete from topic where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(Topic obj) {

    }

    @Override
    public void update(Topic obj) {

        String sql = "update topic set topic_title=? where id=?";
        execute(sql,obj.getTopicTitle(),obj.getId());
    }

    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) from topic";
        return count(sql,detachedCriteria);
    }
}
