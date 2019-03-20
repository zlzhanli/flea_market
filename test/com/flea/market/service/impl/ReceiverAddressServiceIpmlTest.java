package com.flea.market.service.impl;

import com.flea.market.dao.ReceiverAddressDAO;
import com.flea.market.dao.base.DAOFactory;
import com.flea.market.pojo.ReceiverAddress;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiverAddressServiceIpmlTest {

    @Test
    public void getById() {
        ReceiverAddressDAO receiverAddressDao = (ReceiverAddressDAO) DAOFactory.createFactory().create("receiverAddressDao");
        ReceiverAddress byId = receiverAddressDao.findById(16);
        System.out.println(byId);
    }

    @Test
    public void addAddress() {
    }

    @Test
    public void listAddressByCustomerId() {
    }

    @Test
    public void delById() {
    }

    @Test
    public void getById1() {
    }

    @Test
    public void update() {
    }
}