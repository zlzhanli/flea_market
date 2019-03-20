package com.flea.market.service.impl;

import com.flea.market.dao.CustomerDAO;
import com.flea.market.dao.ForumDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.Forum;
import com.flea.market.pojo.Goods;
import com.flea.market.service.ForumService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public class ForumServiceImpl implements ForumService {
    @Resource(name = "forumDAO")
    private ForumDAO forumDAO;
    @Resource(name = "customerDAO")
    private CustomerDAO customerDAO;


    @Override
    public Result add(Forum forum) {
        forumDAO.save(forum);
        Result result = new Result();
        if (forum.getId() > 0) {
            result.setCode(200);
            result.setMsg("发帖成功");
        } else {
            result.setCode(100);
            result.setMsg("发帖失败");
        }
        return result;
    }

    @Override
    public PageBean<Forum> listByBlockId(Integer blockId, Integer page) {

        DetachedCriteria detachedCriteria = new DetachedCriteria();
        detachedCriteria.add("forum_block", new DetachedCriteria.FactorImp<>(blockId));
        detachedCriteria.add("forum_status",new DetachedCriteria.FactorImp<>(0));
        detachedCriteria.setOrder(DetachedCriteria.DESC);
        detachedCriteria.setOrderColumn("forum_modify_time");

        PageBean<Forum> pageBean = new PageBean<>();
        // 设置当前页数:
        Integer currPage = page;
        Integer pageSize = 15;
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数：
        pageBean.setPageSize(pageSize);
        // 设置总记录数:
        Integer totalCount = forumDAO.count(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        // 设置总页数：
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 设置每页显示数据的集合:
        Integer begin = (currPage - 1) * pageSize;

        System.out.println(begin);
        System.out.println(pageSize);
        List<Forum> list = forumDAO.listByPage(detachedCriteria, (page - 1) * 15, 15);
        for (Forum forum : list) {
            Customer customer_res = new Customer();
            setValueToRes(forum, customer_res);
        }
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public PageBean<Forum> list(DetachedCriteria detachedCriteria, Integer page, Integer rows) {
        PageBean<Forum> pageBean = new PageBean<>();
        // 设置当前页数:
        Integer currPage = page;
        Integer pageSize = rows;
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数：
        pageBean.setPageSize(pageSize);
        // 设置总记录数:
        Integer totalCount = forumDAO.count(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        // 设置总页数：
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 设置每页显示数据的集合:
        Integer begin = (currPage - 1) * pageSize;

        List<Forum> list = forumDAO.listByPage(detachedCriteria, begin, pageSize);
        for (Forum forum : list) {
            Customer customer_res = new Customer();
            setValueToRes(forum, customer_res);
        }
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public Forum findById(Integer id) {
        Forum forum = forumDAO.findById(id);
        Customer customer_res = new Customer();
        setValueToRes(forum, customer_res);
        return forum;
    }

    @Override
    public void addView(Integer id) {
        forumDAO.addView(id);
    }

    @Override
    public List<Forum> listForumByCustomerId(Integer id) {
        return forumDAO.listByCustomerId(id);
    }

    @Override
    public Result userDelForum(Integer id) {
        Result result = new Result();
        result.setMsg("删除成功");
        result.setCode(200);
        if (forumDAO.updateStatus(id, 1) <= 0) {
            result.setMsg("删除失败，请稍后再试");
            result.setCode(100);
        }
        return result;
    }

    @Override
    public Result adminDelForum(Integer id) {
        Result result = new Result();
        result.setMsg("删除成功");
        result.setCode(200);
        if (forumDAO.updateStatus(id, 2) <= 0) {
            result.setMsg("删除失败，请稍后再试");
            result.setCode(100);
        }
        return result;
    }

    @Override
    public Integer countForumByBlockId(Integer id) {
        DetachedCriteria detachedCriteria = new DetachedCriteria();
        detachedCriteria.add("forum_status", new DetachedCriteria.FactorImp(0));
        detachedCriteria.add("forum_block", new DetachedCriteria.FactorImp(id));
        return forumDAO.count(detachedCriteria);
    }

    //将用户信息中需要的内容带回
    private void setValueToRes(Forum forum, Customer customer_res) {
        Customer customer = customerDAO.findById(forum.getForumAuthor());
        customer_res.setId(customer.getId());
        customer_res.setNickName(customer.getNickName());
        customer_res.setPhoto(customer.getPhoto());
        customer_res.setPhone(customer.getPhone());
        customer_res.setEmail(customer.getEmail());
        forum.setCustomer(customer_res);
    }
    //TODO 刘天佑
}

