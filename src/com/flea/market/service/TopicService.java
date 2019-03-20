package com.flea.market.service;

import com.flea.market.pojo.Topic;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/6
 */
public interface TopicService {
    @AutoCommit
    @AutoClose
    List<Topic> list();


    @AutoClose
    @AutoCommit
   List<Topic>  listByString(String s);
}
