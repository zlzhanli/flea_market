package com.flea.market.web.action;

import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.ReceiverAddress;
import com.flea.market.service.ReceiverAddressService;
import com.flea.market.util.Fordword;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author LiuTianyou
 * @date 2019/3/7
 */

public class ReceiverAddressAction {
    private String name;
    private String phone;
    private Integer province;
    private Integer city;
    private Integer area;
    private String detailAddr;
    private String addr;
    private Integer isDefault;
    private Integer customerId;
    public Integer id;

    @Resource(name = "receiverAddressService")
    private ReceiverAddressService addressService;

    //添加地址
    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = new Result();
        ReceiverAddress receiverAddress = getReceiverAddress();
        return JSONObject.fromObject(addressService.addAddress(receiverAddress)).toString();
    }

    private ReceiverAddress getReceiverAddress() {
        ReceiverAddress receiverAddress = new ReceiverAddress();
        receiverAddress.setId(id);
        receiverAddress.setReceiverName(name);
        receiverAddress.setReceiverPhone(phone);
        receiverAddress.setArea(area);
        receiverAddress.setAddr(addr);
        receiverAddress.setProvince(province);
        receiverAddress.setCity(city);
        receiverAddress.setDetial(detailAddr);
        receiverAddress.setIsDefault(isDefault);
        receiverAddress.setCustomerId(customerId);
        return receiverAddress;
    }

    //通过用户id 获取所有的地址
    public String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer= (Customer) request.getSession().getAttribute("customer");
        List<ReceiverAddress> address = addressService.listAddressByCustomerId(customer.getId());

        return JSONArray.fromObject(address).toString();
    }
    @Fordword
    public String addressUI(HttpServletRequest request, HttpServletResponse response) {
        return "jsp/address.jsp";
    }

    //删除用户地址
    public String del(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (addressService.remove(id)) {
            return "200";
        }
        return "100";
    }

    //通过地址id 获取地址
    public String getAddressById(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ReceiverAddress address = addressService.getById(id);

        return JSONObject.fromObject(addressService.getById(id)).toString();
    }

    //修改地址

    public String update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReceiverAddress receiverAddress = getReceiverAddress();
        Result<?> result = addressService.update(receiverAddress);
        return JSONObject.fromObject(result).toString();
    }




    //从服务器获取Session中用户的id
    public String getCustomId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer c = (Customer) request.getSession().getAttribute("customer");
        return c.getId().toString();
    }


    //通过用户id 获取所有的地址 getList
    public String getAddressList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer= (Customer) request.getSession().getAttribute("customer");
        List<ReceiverAddress> address = addressService.listAddressByCustomerId(customer.getId());

        return JSONArray.fromObject(address).toString();
    }





}
