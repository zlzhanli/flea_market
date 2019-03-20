package com.flea.market.web.action;

import com.flea.market.pojo.Administrator;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class AdministratorActionTest {

    @Test
    public void login() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        AdministratorAction o = (AdministratorAction) ActionFactory.createFactory().create("administrator");
//        o.login(null, null);
        Method method = o.getClass().getMethod("login1", HttpServletRequest.class, HttpServletResponse.class);

    }
}