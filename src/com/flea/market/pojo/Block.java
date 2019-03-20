package com.flea.market.pojo;



import com.flea.market.util.Column;

import java.util.Objects;

/**
 * 板块实体
 *
 * @author karl
 * @date 2019-03-06
 */
public class Block {

    private Integer id;
    private String blockName;
    private String blockText;
    private String blockCover;
    private String blockTopic;
    private Integer blockAllForum;
    private Integer blockAllComment;
    private  Integer todayActive;

    public Integer getTodayActive() {
        return todayActive;
    }

    public void setTodayActive(Integer todayActive) {
        this.todayActive = todayActive;
    }

    public Integer getBlockAllForum() {
        return blockAllForum;
    }

    @Column(name="block_all_forum")
    public void setBlockAllForum(Integer blockAllForum) {
        this.blockAllForum = blockAllForum;
    }

    public Integer getBlockAllComment() {
        return blockAllComment;
    }
    @Column(name="block_all_comment")
    public void setBlockAllComment(Integer blockAllComment) {
        this.blockAllComment = blockAllComment;
    }

    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     * @param id 主键
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }



    public String getBlockName() {
        return blockName;
    }

    /**
     * 设置板块名称
     *
     * @param blockName 板块名称
     */
    @Column(name = "block_name")
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }



    public String getBlockText() {
        return blockText;
    }

    /**
     * 设置板块描述
     *
     * @param blockText 板块描述
     */
    @Column(name = "block_text")
    public void setBlockText(String blockText) {
        this.blockText = blockText;
    }



    public String getBlockCover() {
        return blockCover;
    }

    /**
     * 设置板块封面
     *
     * @param blockCover 板块封面
     */
    @Column(name = "block_cover")
    public void setBlockCover(String blockCover) {
        this.blockCover = blockCover;
    }



    public String getBlockTopic() {
        return blockTopic;
    }

    /**
     * 设置板块主题
     * 板块主题：一个板块可以有多个板块，板块主题存储的是主题表中主题的主键，使用 | 分开
     * eg：1|2|3|4 对应的是主题一|主题二|主题三|主题四
     *
     * @param blockTopic 板块主题
     */
    @Column(name="block_topic")
    public void setBlockTopic(String blockTopic) {
        this.blockTopic = blockTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Block block = (Block) o;
        return Objects.equals(id, block.id) &&
                Objects.equals(blockName, block.blockName) &&
                Objects.equals(blockText, block.blockText) &&
                Objects.equals(blockCover, block.blockCover) &&
                Objects.equals(blockTopic, block.blockTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blockName, blockText, blockCover, blockTopic);
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", blockName='" + blockName + '\'' +
                ", blockText='" + blockText + '\'' +
                ", blockCover='" + blockCover + '\'' +
                ", blockTopic='" + blockTopic + '\'' +
                ", blockAllForum=" + blockAllForum +
                ", blockAllComment=" + blockAllComment +
                ", todayActive=" + todayActive +
                "}\n";
    }
}
