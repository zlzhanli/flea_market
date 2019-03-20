package com.flea.market.dao.impl;

import com.flea.market.dao.BlockDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.pojo.Block;
import org.junit.Test;

import java.util.List;



public class BlockDAOImplTest {

    @Test
    public void list() {
        BlockDAO blockDAO = (BlockDAO) DAOFactory.createFactory().create("blockDAO");
        List<Block> list = blockDAO.list();
        System.out.println(list);
    }

    @Test
    public void listByPage() {
    }

    @Test
    public void findById() {
        BlockDAO blockDAO = (BlockDAO) DAOFactory.createFactory().create("blockDAO");
        System.out.println(blockDAO.findById(1));
    }

    @Test
    public void save() {
        BlockDAO blockDAO = (BlockDAO) DAOFactory.createFactory().create("blockDAO");
        Block block = new Block();
        block.setBlockText("nihao");
        blockDAO.save(block);
    }

    @Test
    public void delete() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
    }

    @Test
    public void count() {
        BlockDAO blockDAO = (BlockDAO) DAOFactory.createFactory().create("blockDAO");
        System.out.println(blockDAO.count(new DetachedCriteria()));
    }
}