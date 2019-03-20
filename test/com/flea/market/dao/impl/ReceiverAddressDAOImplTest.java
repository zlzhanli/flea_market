package com.flea.market.dao.impl;

import com.flea.market.dao.ReceiverAddressDAO;
import com.flea.market.dao.base.DAOFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiverAddressDAOImplTest {

    @Test
    public void listByCustomerId() {
        ReceiverAddressDAO receiverAddressDao = (ReceiverAddressDAO) DAOFactory.createFactory().create("receiverAddressDao");
        System.out.println(receiverAddressDao.listByCustomerId(1));
    }
}