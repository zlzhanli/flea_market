package com.flea.market.web.action;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReceiverAddressActionTest {

    @Test
    public void getAddressById() throws IllegalAccessException, IOException, InstantiationException {
        ReceiverAddressAction receiverAddressAction= (ReceiverAddressAction) ActionFactory.createFactory().create("address");
        receiverAddressAction.id=19;
      System.out.println(receiverAddressAction.getAddressById(null,null));   ;
    }
}