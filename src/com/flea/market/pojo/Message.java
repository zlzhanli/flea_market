package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Date;
import java.util.Objects;

/**
 * 用户留言表实体类
 *
 * @author SunTao
 * @Date 2019/3/6
 */
public class Message {

    private Integer id;
    private Integer messageWriterId;
    private Integer messageReaderId;
    private String messageContent;
    private Date messageCreateDate;
    private Integer messageStatus;


    public Integer getId() {
        return id;
    }

    /**
     * 留言表id
     *
     * @param id 留言表id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getMessageWriterId() {
        return messageWriterId;
    }

    /**
     * 写留言的用户id
     *
     * @param messageWriterId 写留言的用户id
     */
    @Column(name = "message_writer_id")
    public void setMessageWriterId(Integer messageWriterId) {
        this.messageWriterId = messageWriterId;
    }


    public Integer getMessageReaderId() {
        return messageReaderId;
    }

    /**
     * 读留言的用户id
     *
     * @param messageReaderId 读留言的用户id
     */
    @Column(name = "message_reader_id")
    public void setMessageReaderId(Integer messageReaderId) {
        this.messageReaderId = messageReaderId;
    }


    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 留言内容
     *
     * @param messageContent 留言内容
     */
    @Column(name = "message_content")
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }


    public Date getMessageCreateDate() {
        return messageCreateDate;
    }

    /**
     * 创建时间
     *
     * @param messageCreateDate 创建时间
     */
    @Column(name = "message_create_date")
    public void setMessageCreateDate(Date messageCreateDate) {
        this.messageCreateDate = messageCreateDate;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    /**
     * 留言状态， 未读留言，已读，已删除
     *
     * @param messageStatus 留言状态
     */
    @Column(name = "message_status")
    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return id.equals(message.id) &&
                messageWriterId.equals(message.messageWriterId) &&
                messageReaderId.equals(message.messageReaderId) &&
                messageStatus.equals(message.messageStatus) &&
                Objects.equals(messageContent, message.messageContent) &&
                Objects.equals(messageCreateDate, message.messageCreateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageWriterId, messageReaderId, messageContent, messageCreateDate, messageStatus);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messageWriterId=" + messageWriterId +
                ", messageReaderId=" + messageReaderId +
                ", messageContent='" + messageContent + '\'' +
                ", messageCreateDate=" + messageCreateDate +
                ", messageStatus=" + messageStatus +
                '}';
    }
}
