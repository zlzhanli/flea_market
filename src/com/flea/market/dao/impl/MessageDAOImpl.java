package com.flea.market.dao.impl;

import com.flea.market.dao.MessageDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Message;

import java.util.List;

/**
 * @author: zhh
 * @time: 2019/3/7 14:51
 */

public class MessageDAOImpl extends SupBaseDAO<Message> implements MessageDAO {
    /**
     * zhh pass
     */
    @Override
    public List<Message> list() {

        String sql = "select * from message";
        return list(sql,Message.class);
    }

    @Override
    public List<Message> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {

        return listByPage(detachedCriteria,begin,pageSize);
    }
    /**
     * zhh pass
     */
    @Override
    public Message findById(Integer id) {
        String sql = "select * from message where id=?";
        return find(sql,Message.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(Message obj) {

        String sql = "insert into message values(null,?,?,?,?,?)";
        execute(sql,obj.getMessageWriterId(),obj.getMessageReaderId(),obj.getMessageContent(),obj.getMessageCreateDate(),obj.getMessageStatus());

    }
    /**
     * zhh pass
     */
    @Override
    public void delete(Message obj) {
        String sql = "delete from message where id=?";
        execute(sql,obj.getId());

    }

    @Override
    public void remove(Message obj) {

    }
    /**
     * zhh pass
     */
    @Override
    public void update(Message obj) {

        String sql = "update message set message_writer_id=?,message_reader_id=?,message_content=?,message_create_date=?,message_status=? where id=?";
        execute(sql,obj.getMessageWriterId(),obj.getMessageReaderId(),obj.getMessageContent(),obj.getMessageCreateDate(),obj.getMessageStatus(),obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) from message";
        return count(sql,detachedCriteria);
    }
}
