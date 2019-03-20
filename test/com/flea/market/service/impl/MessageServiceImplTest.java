package com.flea.market.service.impl;

import com.flea.market.service.MessageService;
import com.flea.market.service.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageServiceImplTest {

    @Test
    public void getUnread() {
        MessageService messageService = (MessageService) ServiceFactory.createFactory().create("messageService");

    }

    @Test
    public void getalreadyRead() {
    }

    @Test
    public void waite() {
    }

    @Test
    public void alreadyRead() {
    }
}