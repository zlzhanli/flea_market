package com.flea.market.service;

import com.flea.market.pojo.Block;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public interface BlockService {
    @AutoCommit
    @AutoClose
    List<Block> list();

    @AutoCommit
    @AutoClose
    Block findById(int id);

}
