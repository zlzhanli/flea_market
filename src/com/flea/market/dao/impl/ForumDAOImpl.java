package com.flea.market.dao.impl;

import com.flea.market.dao.ForumDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Forum;

import java.util.List;

/**
 * @author: zhh pass
 * @time: 2019/3/7 14:42
 */

public class ForumDAOImpl extends SupBaseDAO<Forum> implements ForumDAO {
    /**
     * zhh pass
     */
    @Override
    public List<Forum> list() {
        String sql = "select * from forum";
        return list(sql,Forum.class);
    }

    @Override
    public List<Forum> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria=detachedCriteria.clone();
        detachedCriteria.setPageing(true,begin,pageSize);
        return listByPage("select * from forum ",Forum.class,detachedCriteria);
    }
    /**
     * zhh pass
     */
    @Override
    public Forum findById(Integer id) {
        String sql = "select * from forum where id=?";
        return find(sql,Forum.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(Forum obj) {

        String sql = "insert into forum values(null,?,?,now(),?,?,0,?,?,now(),0)";
        Integer id = execute(sql, obj.getForumTitle(), obj.getForumContent(),
                obj.getForumBlock(), obj.getForumAuthor(),
                obj.getForumCommentCount(), obj.getForumTopic());
        obj.setId(id);
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(Forum obj) {
        String sql = "delete from forum where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(Forum obj) {
    }
    /**
     * zhh pass
     */
    @Override
    public void update(Forum obj) {

        String sql = "update forum set forum_title=?,forum_content=?," +
                "forum_gmt_time=?,forum_block=?,forum_author=?,forum_page_view=?," +
                "forum_comment_count=?,forum_topic=? where id=?";
        execute(sql,obj.getForumTitle(),obj.getForumContent(),
                obj.getForumGmtTime(),obj.getForumBlock(),obj.getForumAuthor(),
                obj.getForumPageView(),obj.getForumCommentCount(),obj.getForumTopic(),obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) as count from forum ";
        return count(sql,detachedCriteria);
    }


    @Override
    public void updateCommentNumAndModify(Integer forumId) {

    }

    @Override
    public int countToday(Integer blockId) {
        String sql="select count(*) as count from forum where TO_DAYS(forum_modify_time)=TO_DAYS(now()) and forum_block="+blockId;
       return count(sql,new DetachedCriteria());

    }

    @Override
    public List<Forum> listByBlockId(Integer blockId) {
        String sql="select * from forum where forum_block=? order by forum_modify_time desc";
        return list(sql,Forum.class,blockId);

    }

    @Override
    public void addView(Integer id) {
        System.out.println("添加到数据库");
        execute("update forum set forum_page_view = forum_page_view+1 where id= ? ",id);
    }

    @Override
    public List<Forum> listByCustomerId(Integer id) {
        return  list("SELECT * FROM forum where  forum_author = ? and forum_status != 1  ORDER BY forum_gmt_time desc",Forum.class,id);
    }

    @Override
    public int updateStatus(Integer forumId, Integer status) {
        return execute2("update forum set forum_status=? where id= ?",status,forumId);
    }
}
