package com.flea.market.service.impl;

import com.flea.market.pojo.Topic;
import com.flea.market.service.ServiceFactory;
import com.flea.market.service.TopicService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TopicServiceImplTest {

    @Test
    public void list() {
        TopicService topicService = (TopicService) ServiceFactory.createFactory().create("topicService");
        List<Topic> list = topicService.list();
        System.out.println(list);
    }
}