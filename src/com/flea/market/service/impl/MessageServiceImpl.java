package com.flea.market.service.impl;

import com.flea.market.entity.Result;
import com.flea.market.pojo.Message;
import com.flea.market.service.MessageService;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public class MessageServiceImpl implements MessageService {
    @Override
    public List<Message> getUnread(Integer readCustomerId) {
        // TODO 刘天佑
        return null;
    }

    @Override
    public List<Message> getAlreadyRead(Integer readCustomerId) {
        // TODO 刘天佑
        return null;
    }

    @Override
    public Result<Message> waite(Integer waiteCustomerId, Message message) {
        // TODO 刘天佑
        return null;
    }

    @Override
    public Result<Message> alreadyRead(Message message) {
        // TODO 刘天佑
        return null;
    }
}
