package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Date;
import java.util.Objects;


/**
 * 论坛发帖的表的实体类
 *
 * @author SunTao
 * @Date 2019/3/6
 */
public class Forum {

    private Integer id;
    private String forumTitle;
    private String forumContent;
    private Date forumGmtTime;
    private Integer forumBlock;
    private Integer forumAuthor;
    private Integer forumPageView;
    private Integer forumCommentCount;
    private Integer forumTopic;
    private Date forumModifyTime;
    private  Integer forumStatus;
    private Customer customer;
    public int getForumStatus() {
        return forumStatus;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name="forum_status")
    public void setForumStatus(Integer forumStatus) {
        this.forumStatus = forumStatus;
    }

    public Date getForumModifyTime() {
        return forumModifyTime;
    }
    @Column(name="forum_modify_time")
    public void setForumModifyTime(Date forumModifyTime) {
        this.forumModifyTime = forumModifyTime;
    }

    public Integer getId() {
        return id;
    }

    /**
     * 论坛表的id
     * @param id 论坛表的id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public String getForumTitle() {
        return forumTitle;
    }

    /**
     * 论坛发帖标题
     * @param forumTitle 标题
     */
    @Column(name = "forum_title")
    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }


    public String getForumContent() {
        return forumContent;
    }

    /**
     * 帖子的内容
     * @param forumContent 内容
     */
    @Column(name = "forum_content")
    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }


    public Date getForumGmtTime() {
        return forumGmtTime;
    }

    /**
     * 发帖时间
     * @param forumGmtTime 发帖时间
     */
    @Column(name = "forum_gmt_time")
    public void setForumGmtTime(Date forumGmtTime) {
        this.forumGmtTime = forumGmtTime;
    }


    public Integer getForumBlock() {
        return forumBlock;
    }

    /**
     * 发帖的模块编号
     * @param forumBlock 模块编号
     */
    @Column(name = "forum_block")
    public void setForumBlock(Integer forumBlock) {
        this.forumBlock = forumBlock;
    }


    public Integer getForumAuthor() {
        return forumAuthor;
    }

    /**
     * 帖子的作者
     * @param forumAuthor 作者
     */
    @Column(name = "forum_author")
    public void setForumAuthor(Integer forumAuthor) {
        this.forumAuthor = forumAuthor;
    }


    public Integer getForumPageView() {
        return forumPageView;
    }

    /**
     * 帖子的浏览量
     * @param forumPageView 浏览量
     */
    @Column(name = "forum_page_view")
    public void setForumPageView(Integer forumPageView) {
        this.forumPageView = forumPageView;
    }


    public Integer getForumCommentCount() {
        return forumCommentCount;
    }

    /**
     * 帖子的浏览数
     * @param forumCommentCount 浏览量
     */
    @Column(name = "forum_comment_count")
    public void setForumCommentCount(Integer forumCommentCount) {
        this.forumCommentCount = forumCommentCount;
    }


    public Integer getForumTopic() {
        return forumTopic;
    }

    /**
     * 主题
     * @param forumTopic 主题
     */
    @Column(name = "forum_topic")
    public void setForumTopic(Integer forumTopic) {
        this.forumTopic = forumTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Forum forum = (Forum) o;
        return Objects.equals(id, forum.id) &&
                Objects.equals(forumTitle, forum.forumTitle) &&
                Objects.equals(forumContent, forum.forumContent) &&
                Objects.equals(forumGmtTime, forum.forumGmtTime) &&
                Objects.equals(forumBlock, forum.forumBlock) &&
                Objects.equals(forumAuthor, forum.forumAuthor) &&
                Objects.equals(forumPageView, forum.forumPageView) &&
                Objects.equals(forumCommentCount, forum.forumCommentCount) &&
                Objects.equals(forumTopic, forum.forumTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forumTitle, forumContent, forumGmtTime, forumBlock, forumAuthor, forumPageView, forumCommentCount, forumTopic);
    }
    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", forumTitle='" + forumTitle + '\'' +
                ", forumContent='" + forumContent + '\'' +
                ", forumGmtTime=" + forumGmtTime +
                ", forumBlock=" + forumBlock +
                ", forumAuthor=" + forumAuthor +
                ", forumPageView=" + forumPageView +
                ", forumCommentCount=" + forumCommentCount +
                ", forumTopic=" + forumTopic +
                ", forumModifyTime=" + forumModifyTime +
                ", forumStatus=" + forumStatus +
                ", customer=" + customer +
                "}\n";
    }
}
