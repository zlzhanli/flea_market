package com.flea.market.service.impl;

import com.flea.market.dao.BlockDAO;
import com.flea.market.dao.ForumDAO;
import com.flea.market.pojo.Block;
import com.flea.market.service.BlockService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhh
 * @time: 2019/3/11 14:36
 */

public class BlockServiceImpl implements BlockService {
    @Resource(name="blockDAO")
    private BlockDAO blockDAO;
    @Resource(name="forumDAO")
    private ForumDAO forumDAO;


    @Override
    public List<Block> list() {
        List<Block> list=blockDAO.list();
        for(Block b :list){
            b.setTodayActive(forumDAO.countToday(b.getId()));
        }
        return list;
    }

    @Override
    public Block findById(int id) {
        Block block = blockDAO.findById(id);
        block.setTodayActive(forumDAO.countToday(block.getId()));
        return block;
    }


}
