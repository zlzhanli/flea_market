package com.flea.market.web.action;

import com.flea.market.pojo.Topic;
import com.flea.market.service.TopicService;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/6
 */
public class TopicAction {
    String topicStr;
    @Resource(name = "topicService")
    private TopicService topicService;

    public String test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSONArray.fromObject(topicService.list()).toString();
    }
    public String test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "12312";
    }
    public String listTopicByString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSONArray.fromObject(topicService.listByString(topicStr)).toString();
    }
}
