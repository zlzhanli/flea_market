package com.flea.market.service;

import com.flea.market.entity.Result;
import com.flea.market.pojo.ReceiverAddress;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

public interface ReceiverAddressService {
    @AutoClose
    @AutoCommit
    Result<?> addAddress(ReceiverAddress receiverAddress);

    /**
     * 通过用户Id 查询地址
     * @param id
     * @return
     */
    @AutoCommit
    @AutoClose
    List<ReceiverAddress> listAddressByCustomerId(Integer id);

    /**
     * 通过id 删除地址
     * @param id
     * @return
     */
    @AutoCommit
    @AutoClose
    boolean remove( int id);

    @AutoCommit
    @AutoClose
    ReceiverAddress getById(int id);

    @AutoClose
    @AutoCommit
    Result update(ReceiverAddress addr);

}
