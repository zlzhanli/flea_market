package com.flea.market.service.impl;

import com.flea.market.dao.CommentDAO;
import com.flea.market.dao.CustomerDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Comment;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.Forum;
import com.flea.market.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhh
 * @time: 2019/3/11 14:37
 */

public class CommentServiceImpl implements CommentService {

    @Resource(name="commentDAO")
    private CommentDAO commentDAO;
    @Resource(name="customerDAO")
    private CustomerDAO customerDAO;

    @Override
    public Result addComment(Comment comment) {
        commentDAO.save(comment);
        Result result=new Result();
        if(comment.getId()>0){
            result.setMsg("添加成功");
            result.setCode(200);
            //设置customer
            comment=commentDAO.findById(comment.getId());
            setValueToComment(comment);
            result.setTarget(comment);
        }else{
            result.setCode(100);
            result.setMsg("回复失败，请稍后再试");
        }
        return result;
    }

    @Override
    public PageBean<Comment> listByForumId(Integer id,Integer page,Integer pageSize) {
        if(null==pageSize){
            pageSize=10;
        }
        DetachedCriteria detachedCriteria=new DetachedCriteria();
        detachedCriteria.add("post_id",new DetachedCriteria.FactorImp<>(id));
        PageBean<Comment> pageBean = new PageBean<>();
        // 设置当前页数:
        Integer currPage =page ;
        pageBean.setCurrPage(currPage);
        // 设置每页显示记录数：
        pageBean.setPageSize(pageSize);
        // 设置总记录数:
        Integer totalCount =commentDAO.count(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        // 设置总页数：
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        // 设置每页显示数据的集合:
        Integer begin = (currPage - 1) * pageSize;

        System.out.println(begin);
        System.out.println(pageSize);
        List<Comment> list=commentDAO.listByPage(detachedCriteria,(page-1)*pageSize,pageSize);

        for(Comment comm :list){
            setValueToComment(comm);
        }
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public int countCommentByForumId(Integer forumId) {
        DetachedCriteria detachedCriteria=new DetachedCriteria();
        detachedCriteria.add("post_id",new DetachedCriteria.FactorImp(forumId));
        return commentDAO.count(detachedCriteria);
    }

    public void setValueToComment(Comment comment){
        Comment parent=null;
        //设置 Comment 的 Customer 对象
        Customer customer=customerDAO.findById(comment.getCommentCustomerId());
        Customer tem_customer=new Customer();
        tem_customer.setId(customer.getId());
        tem_customer.setNickName(customer.getNickName());
        tem_customer.setPhoto(customer.getPhoto());
        comment.setCustomer(tem_customer);
        // 设置Comment 的 Parent的Customer对象
        if(null!=comment.getParentId()){
            parent= commentDAO.findById(comment.getParentId());
            customer= customerDAO.findById(parent.getCommentCustomerId());
            tem_customer.setId(customer.getId());
            tem_customer.setNickName(customer.getNickName());
            tem_customer.setPhoto(customer.getPhoto());
            parent.setCustomer(tem_customer);
            comment.setParent(parent);
        }
        //设置 Comment的  parent 对象


    }





}
