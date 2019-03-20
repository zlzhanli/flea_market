package com.flea.market.dao.impl;

import com.flea.market.dao.ReceiverAddressDAO;
import com.flea.market.dao.base.BaseDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.dao.base.SupBaseDAO;
import com.flea.market.pojo.ReceiverAddress;

import java.util.List;

/**
 * @author LiuTianyou
 * @date 2019/3/7
 */

public class ReceiverAddressDAOImpl extends SupBaseDAO<ReceiverAddress> implements ReceiverAddressDAO {
    @Override
    public int addAddress(ReceiverAddress obj) {
        String sql="insert into receiver_address values(null,?,?,?,?,?,?,?,?,?,?,?,1)";
        Integer id=execute(sql,obj.getCustomerId(),obj.getReceiverName(),obj.getReceiverSex(),obj.getReceiverPhone(),
                obj.getProvince(),obj.getCity(),obj.getArea(),obj.getDetial(),obj.getIsDefault(),obj.getAddressLabel(),obj.getAddr());
        obj.setId(id);
        return id;
    }
    @Override
    public List<ReceiverAddress> listByCustomerId(Integer id) {
        String sql= "select * from receiver_address where customer_id = ? and `status`= ? order by is_default desc";
        return list(sql,ReceiverAddress.class,id,1);
    }
    @Override
    public List<ReceiverAddress> list() {
        String sql = "select * from receiver_address";
        return list(sql,ReceiverAddress.class);
    }

    @Override
    public List<ReceiverAddress> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return listByPage(detachedCriteria,begin,pageSize);
    }
    @Override
    public ReceiverAddress findById(Integer id) {
      return  find("select * from receiver_address where id=?",ReceiverAddress.class,id);
    }

    @Override
    public int updateAddr(ReceiverAddress obj) {

        return 0;
    }
    @Override
    public int remove(int id) {
        return  execute2("update receiver_address set status=? where id=?",0,id);
    }

    @Override
    public int resetAllStatus(int customerId) {
        return  execute2("update receiver_address set is_default=0 where customer_id=?",customerId);
    }

    @Override
    public void save(ReceiverAddress obj) {

    }
    @Override
    public void delete(ReceiverAddress obj) {
    }

    @Override
    public void remove(ReceiverAddress obj) {
        String sql = "delete from receiver_address where id=?";
        execute(sql,obj.getId());
    }

    @Override
    public void update(ReceiverAddress obj) {
        System.out.println("运行到Dao，进行数据修改");
        String sql="update receiver_address set receiver_name=?,\n" +
                "receiver_phone=?,province=?,city=?,area=?,detail=?,is_default=?," +
                "addr=? where id=?\n";
        execute(sql,obj.getReceiverName(),obj.getReceiverPhone(),obj.getProvince(),
                obj.getCity(),obj.getArea(),obj.getDetial(),obj.getIsDefault(),obj.getAddr(),obj.getId()
                );
    }

    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        String sql = "select count(id) from receiver_address";
        return count(sql,detachedCriteria);
    }
}
