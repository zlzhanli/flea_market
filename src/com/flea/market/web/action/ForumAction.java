package com.flea.market.web.action;

import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.Forum;
import com.flea.market.service.ForumService;
import com.flea.market.util.Fordword;
import com.flea.market.util.JSONUtil;
import com.flea.market.util.Redirect;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiuTianyou
 * @date 2019/3/14
 */

public class ForumAction {
    //帖子的iD
    private Integer blockId;
    private Integer topic;
    private Integer currentPage;
    private Integer total;
    private Integer id;
    private Integer customerId;
    private Integer status;
    /**
     * 帖子的内容
     */
    private String content;

    private String captcha;

    private String title;
    // 当前页数
    private Integer page;
    // 每页显示记录数
    private Integer rows;


    @Resource(name = "forumService")
    private ForumService forumService;

    private void initPage() {
        if (rows == null) {
            rows = 15;
        }
        if (page == null) {
            page = 1;
        }
    }

    /**
     * 根据版块的编号 返回帖子列表
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public String getListByBlock(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PageBean bean = forumService.listByBlockId(blockId, page);

        return JSONObject.fromObject(bean).toString();
    }

    public String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initPage();
        Map<String, Object> map = new HashMap<>();
        DetachedCriteria criteria = new DetachedCriteria();
        if (status != null ) {
            criteria.add("forum_status", new DetachedCriteria.FactorImp<Integer>(status));
        }
        if (blockId != null ) {
            criteria.add("forum_block", new DetachedCriteria.FactorImp<Integer>(blockId));
        }
        if (topic != null ) {
            criteria.add("forum_topic", new DetachedCriteria.FactorImp<Integer>(topic));
        }
        PageBean bean = forumService.list(criteria, page, rows);
        map.put("rows", bean.getList());
        map.put("total", bean.getTotalCount());
        return JSONUtil.toJson1(map).toString();
    }

    @Fordword
    public String showForumList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("blockId", blockId);
        return "jsp/forum_list.jsp";
    }

    public String createForum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = new Result();
        result.setMsg("验证码错误");
        result.setCode(-1);
        if (!((String) request.getSession().getAttribute("captcha")).equalsIgnoreCase(captcha)) {
            return JSONObject.fromObject(result).toString();
        } else {
            Forum forum = new Forum();
            forum.setForumCommentCount(0);
            forum.setForumTopic(topic);
            forum.setForumBlock(blockId);
            forum.setForumTitle(title);
            forum.setForumAuthor(((Customer) request.getSession().getAttribute("customer")).getId());
            forum.setForumContent(content);
            return JSONObject.fromObject(forumService.add(forum)).toString();
        }
    }

    @Fordword
    public String showDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("id", id);
        Forum forum=forumService.findById(id);
        if(forum.getForumStatus()!=0){
            return "jsp/404NOTFOND";
        }
        forumService.addView(id);
        return "jsp/forum_detail.jsp";
    }

    //根据ID 获取主题帖的内容

    public String findForumById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Forum forum=forumService.findById(id);
        if(forum.getForumStatus()!=0){
            return "ERROR";
        }
        return JSONObject.fromObject(forumService.findById(id)).toString();
    }
    public String findById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSONObject.fromObject(forumService.findById(id)).toString();
    }

    //获取一个用户的所有发帖
    public String listForumByCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer= (Customer) request.getSession().getAttribute("customer");
        int id=customer.getId();
        return JSONArray.fromObject(forumService.listForumByCustomerId(id)).toString();
    }


    /**
     * 用户自主删除帖子
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public String delForum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSONObject.fromObject(forumService.userDelForum(id)).toString();
    }

    /**
     * 管理员删除帖子
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public String adminDelForum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return JSONObject.fromObject(forumService.adminDelForum(id)).toString();
    }

    @Fordword
    public String creatForum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/new_post.jsp";

    }

}
