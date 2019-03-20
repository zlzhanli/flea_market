package com.flea.market.service.impl;

import com.flea.market.dao.ReceiverAddressDAO;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.ReceiverAddress;
import com.flea.market.service.ReceiverAddressService;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Delayed;

/**
 * @author LiuTianyou
 * @date 2019/3/7
 */

public class ReceiverAddressServiceIpml implements ReceiverAddressService {
    @Resource(name="receiverAddressDAO")
    private ReceiverAddressDAO addressDAO;
    /**
     * 添加地址
     * @param receiverAddress
     * @return 添加成功与否
     */
    @Override
    public Result addAddress(ReceiverAddress receiverAddress) {
        Result result= new Result();
        //首先判断是不是存在相同的地址
        List<ReceiverAddress> addresses = addressDAO.listByCustomerId(receiverAddress.getCustomerId());
        if (addresses.size() >= 5) {
            result.setCode(-3);
            result.setMsg("最多只能添加5个收货地址。");
            return result;
        }

        for (ReceiverAddress address : addresses) {
            if (address.equals(receiverAddress)) {
                result.setCode(-1);
                result.setMsg("已经存在相同地址，无法重复添加");
                return result;
            }
        }



        if(receiverAddress.getIsDefault()==1){
            addressDAO.resetAllStatus(receiverAddress.getCustomerId());
        }
        //判断是否添加成功
        if (addressDAO.addAddress(receiverAddress)<=0) {
            //TODO
            result.setCode(-2);
            result.setMsg("添加出现问题，请稍后再试");
        } else {
            result.setCode(0);
            result.setMsg("添加成功");
        }

        return result;
    }

    @Override
    public List<ReceiverAddress> listAddressByCustomerId(Integer id) {

        List<ReceiverAddress> addresses = addressDAO.listByCustomerId(id);
        boolean hasDefault=false;
        //查找是否存在默认地址
        for(ReceiverAddress addr :addresses){
            if(addr.getIsDefault()==1){
                hasDefault=true;
                break;
            }
        }
        //不存在默认地址，取出第一条地址设为默认
        if(!hasDefault&& addresses.size()>0){
            addresses.get(0).setIsDefault(1);
            update(addresses.get(0));
            addresses = addressDAO.listByCustomerId(id);
        }
        //返回
        return addresses;






    }

    @Override
    public boolean remove(int id) {
        return addressDAO.remove(id)>0;
    }

    @Override
    public ReceiverAddress getById(int id) {
        return addressDAO.findById(id);
    }
    @Override
    public Result update(ReceiverAddress addr) {
        Result result= new Result();
        //判断是否存在默认地址
        List<ReceiverAddress> addresses = addressDAO.listByCustomerId(addr.getCustomerId());
        int count=0;
        if(addr.getIsDefault()==1){
            count++;
        }
        for(ReceiverAddress address :addresses){
           if(address.getIsDefault()==1){
               count++;
           }
        }
        System.out.println("service count：" +count);
        if(count==0){
            result.setCode(-1);
            result.setMsg("请至少选择一个默认地址");
            return result;
        }
        if(addr.getIsDefault()==1){
            addressDAO.resetAllStatus(addr.getCustomerId());
        }
        addressDAO.update(addr);
        result.setMsg("修改成功");
        result.setCode(0);
        return result;
    }


}
