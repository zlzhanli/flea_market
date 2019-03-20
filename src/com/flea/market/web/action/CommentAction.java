package com.flea.market.web.action;

import com.flea.market.pojo.Comment;
import com.flea.market.pojo.Customer;
import com.flea.market.service.CommentService;
import com.flea.market.util.Fordword;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiuTianyou
 * @date 2019/3/15
 */

public class CommentAction {
    private String content;
    private Integer forumId;
    private Integer parentId;
    private Integer page;
    @Resource(name = "commentService")
    private CommentService commentService;

    public String creatComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Comment comment = new Comment();
        comment.setPostId(forumId);
        comment.setContent(content);
        comment.setParentId(parentId);
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        comment.setCommentCustomerId(customer.getId());
        return JSONObject.fromObject(commentService.addComment(comment)).toString();
    }
    public String listComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSONObject.fromObject(commentService.listByForumId(forumId, page , 10)).toString();
    }
}
