package com.flea.market.service;

import com.flea.market.entity.Result;
import com.flea.market.pojo.Message;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public interface MessageService {

    /**
     * 获取用户未读的留言
     * @param readCustomerId 读取用户的id
     * @return 未读的留言列表
     * @Author li ke guo
     */
    @AutoClose
    @AutoCommit
    List<Message> getUnread(Integer readCustomerId);

    /**
     *
     * 获取用户已读的留言
     * @param readCustomerId 被留言用户id
     * @return 留言列表
     * @Author li ke guo
     */
    @AutoClose
    @AutoCommit
    List<Message> getAlreadyRead(Integer readCustomerId);

    /**
     * 用户留言功能
     * @param waiteCustomerId 留言用户id
     * @param message 留言所需要的信息
     * @return 留言执行结果
     * @Author li ke guo
     */
    @AutoClose
    @AutoCommit
    Result<Message> waite(Integer waiteCustomerId, Message message);

    /**
     * 该方法标记用户已读该信息，用户打开留言执行此操作
     * @param message 需要标记的留言
     * @return 标记执行的结果
     * @Author li ke guo
     */
    @AutoClose
    @AutoCommit
    Result<Message> alreadyRead(Message message);


}
