package com.flea.market.web.action;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TopicActionTest {

    @Test
    public void test1() throws IllegalAccessException, IOException, InstantiationException {

        TopicAction topicAction = (TopicAction) ActionFactory.createFactory().create("topic");
        String test = topicAction.test(null, null);
        System.out.println(test);

    }
}