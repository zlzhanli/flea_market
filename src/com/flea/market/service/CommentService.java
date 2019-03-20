package com.flea.market.service;

import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Comment;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public interface CommentService  {

    @AutoCommit
    @AutoClose
    Result addComment(Comment comment);

    @AutoClose
    @AutoCommit
    PageBean<Comment> listByForumId(Integer id, Integer start, Integer pageSize);


    int  countCommentByForumId(Integer forumId);

}
