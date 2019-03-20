package com.flea.market.service;

import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Forum;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public interface ForumService {

    /**
     * 新建主题帖
     * @param forum
     */
    @AutoCommit
    @AutoClose
    Result add(Forum forum);


    /**
     * 获取一个版块下的所有帖
     * @param blockId
     * @return
     */
    @AutoClose
    @AutoCommit
    PageBean<Forum> listByBlockId(Integer blockId, Integer page);

    @AutoClose
    @AutoCommit
    PageBean<Forum> list(DetachedCriteria detachedCriteria, Integer page, Integer rows);

    @AutoCommit
    @AutoClose
    Forum findById(Integer id);

    /**
     * 添加浏览量
     * @param id
     */
    @AutoClose
    @AutoCommit
    void addView(Integer id);


    /**
     * 查看一个用户的所有发帖
     * @return
     */
    @AutoClose
    @AutoCommit
    List<Forum> listForumByCustomerId(Integer id);

    /**
     * 用户自己删除帖子
     * @param id
     * @return
     */
    @AutoCommit
    @AutoClose
    Result userDelForum(Integer id);


    /**
     * 管理员删除帖子
     * @param id
     * @return
     */
    @AutoCommit
    @AutoClose
    Result adminDelForum(Integer id);


    /**
     * 统计一个版块下的所有帖子
     * @param id
     * @return
     */
    @AutoClose
    @AutoCommit
    Integer countForumByBlockId(Integer id);
}

