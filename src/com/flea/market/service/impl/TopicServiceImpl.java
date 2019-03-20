package com.flea.market.service.impl;

import com.flea.market.dao.TopicDAO;
import com.flea.market.pojo.Topic;
import com.flea.market.service.TopicService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/6
 */
public class TopicServiceImpl implements TopicService {
    @Resource(name = "topicDAO")
    private TopicDAO topicDAO;

    @Override
    public List<Topic> list() {
        return topicDAO.list();
    }


    //通过字符串拆解获得topic 数组
    @Override
    public List<Topic> listByString(String s) {
        List<Topic> list=new ArrayList<>();
        String[] topics=s.split("-");
        System.out.println(Arrays.toString(topics));
        for(String topic :topics){
           Topic tem= topicDAO.findById(Integer.parseInt(topic));
           if(tem!=null){
               list.add(tem);
           }
        }
        return list;
    }
}
