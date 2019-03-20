package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Date;
import java.util.Objects;

/**
 * 评论实体
 *
 * @author karl
 * @date 2019-03-06
 */
public class Comment {

    private Integer id;
    private Integer postId;
    private String content;
    private Date time;
    private Integer parentId;
    private Integer commentCustomerId;
    private  Integer commentStatus;
    private Comment parent;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    @Column(name="comment_status")
    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     * @param id 主键id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPostId() {
        return postId;
    }

    /**
     * 设置论坛id
     * @param postId 论坛id
     */
    @Column(name = "post_id")
    public void setPostId(Integer postId) {
        this.postId = postId;
    }


    public String getContent() {
        return content;
    }

    /**
     * 设置论坛内容
     * @param content 论坛内容
     */
    @Column(name = "content")
    public void setContent(String content) {
        this.content = content;
    }


    public Date getTime() {
        return time;
    }

    /**
     * 设置发帖时间
     * @param time 发帖时间
     */
    @Column(name = "time")
    public void setTime(Date time) {
        this.time = time;
    }


    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置回复评论
     * @param parentId 回复评论
     */
    @Column(name = "parent_id")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public Integer getCommentCustomerId() {
        return commentCustomerId;
    }

    /**
     * 设置回复评论者id
     * @param commentCustomerId 回复者id
     */
    @Column(name = "comment_customer_id")
    public void setCommentCustomerId(Integer commentCustomerId) {
        this.commentCustomerId = commentCustomerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(postId, comment.postId) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(time, comment.time) &&
                Objects.equals(parentId, comment.parentId) &&
                Objects.equals(commentCustomerId, comment.commentCustomerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, content, time, parentId, commentCustomerId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", parentId=" + parentId +
                ", commentCustomerId=" + commentCustomerId +
                '}';
    }
}
