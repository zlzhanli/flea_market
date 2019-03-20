package com.flea.market.pojo;


import com.flea.market.util.Column;

import java.util.Objects;

/**
 * 论坛主题实体
 */
public class Topic {

    private Integer id;
    private String topicTitle;


    public Integer getId() {
        return id;
    }

    /**
     * 论坛 id 主键
     * @param id 主键
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }


    public String getTopicTitle() {
        return topicTitle;
    }

    /**
     * 主题名称
     * @param topicTitle  主题名称
     */
    @Column(name = "topic_title")
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        return id == topic.id &&
                Objects.equals(topicTitle, topic.topicTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicTitle);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topicTitle='" + topicTitle + '\'' +
                '}';
    }
}
