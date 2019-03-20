package com.flea.market.dao.impl;


import com.flea.market.dao.TopicDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.Topic;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.List;

public class TopicDAOImplTest {

    @Test
    public void list() {
        TopicDAO topicDAO = (TopicDAO) DAOFactory.createFactory().create("topicDAO");
        List<Topic> list = topicDAO.list();
        System.out.println(list);

    }    @Test
    public void list2() {
        System.out.println( DigestUtils.md5Hex("123456"));

    }
}