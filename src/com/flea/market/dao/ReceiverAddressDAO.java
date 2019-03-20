package com.flea.market.dao;

import com.flea.market.pojo.ReceiverAddress;

import java.util.List;

public interface ReceiverAddressDAO {
     int addAddress(ReceiverAddress address);

    /**
     * 通过用户id 获取地址
     * @param id
     * @return
     */
     List<ReceiverAddress> listByCustomerId(Integer id);

    /**
     * 通过id 对象
     * @param id
     * @return
     */
     ReceiverAddress findById(Integer id);


    /**
     * 更新地址
     * @param obj
     * @return
     */
    int updateAddr(ReceiverAddress obj);

    /**
     * 从地址表中虚拟删除一个地址
     * @param id 需要删除的id；
     * @return 受影响的行数
     */

    int  remove(int id);

    /**
     * 将所有的收货地址设置为非默认收货地址
     * @param customerId
     * @return
     */


    int resetAllStatus(int customerId);

    /**
     * 更新地址
     * @param addr
     */
    void update(ReceiverAddress addr);

}
