package com.flea.market.dao;

import com.flea.market.dao.base.BaseDAO;
import com.flea.market.pojo.Forum;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author: zhh
 * @time: 2019/3/7 14:41
 */
public interface ForumDAO extends BaseDAO<Forum> {
    /**
     * 修改评论数量
     * @param forumId
     */
    void updateCommentNumAndModify(Integer forumId);
    /**
     * 统计今日活跃的帖子数量
     * @param blockId
     */

    int countToday(Integer blockId);

    List<Forum> listByBlockId(Integer blockId);

    /**
     * 添加浏览量
     * @param Id
     */

    void addView(Integer Id);


    /**
     * 获取一个用户的所有发帖
     * @param id
     * @return
     */
    List<Forum> listByCustomerId(Integer id);


    /**
     * 修改帖子的状态
     * @param forumId
     * @param status
     * @return
     */
    int updateStatus(Integer forumId,Integer status);




}
