package com.flea.market.dao.impl;

import com.flea.market.dao.BlockDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.Block;

import java.util.List;

/**
 * @author: zhh pass
 * @time: 2019/3/7 14:31
 */

public class BlockDAOImpl extends SupBaseDAO<Block> implements BlockDAO {
    /**
     * zhh pass
     */
    @Override
    public List<Block> list() {
        String sql = "select * from block";
        return list(sql,Block.class);
    }

    @Override
    public List<Block> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return null;
    }
    /**
     * zhh pass
     */
    @Override
    public Block findById(Integer id) {
        String sql = "select * from block where id=?";
        return find(sql,Block.class,id);
    }
    /**
     * zhh pass
     */
    @Override
    public void save(Block obj) {
        String sql = "insert into block values(null,?,?,?,?)";
        Integer id = execute(sql,obj.getBlockName(),obj.getBlockText(),obj.getBlockCover(),obj.getBlockTopic());
        obj.setId(id);
    }
    /**
     * zhh pass
     */
    @Override
    public void delete(Block obj) {
        String sql = "delete from block where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void remove(Block obj) {

    }
    /**
     * zhh pass
     */
    @Override
    public void update(Block obj) {

        String sql = "update block set block_name=?,block_text=?,block_cover=?,block_topic=? where id=?" ;
        execute(sql,obj.getBlockName(),obj.getBlockText(),obj.getBlockCover(),obj.getBlockTopic(),obj.getId(),obj.getId());
    }
    /**
     * zhh pass
     */
    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) as count from block";
        return count(sql,detachedCriteria);
    }
}
