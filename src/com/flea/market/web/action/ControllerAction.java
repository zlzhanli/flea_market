package com.flea.market.web.action;

import com.flea.market.util.Fordword;
import com.flea.market.util.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LiuTianyou
 * @date 2019/3/13
 */

public class ControllerAction {
    /**
     * 跳转到首页
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Fordword
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "html/index.html";
    }

    /**
     * 跳转到 APP页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Fordword
    public String app(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "html/app_detail.html";
    }

    /**
     * 跳转到论坛
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Fordword
    public String forum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "html/forum.html";
    }

    /**
     * 跳转到关于我们
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Fordword
    public String aboutUs(HttpServletRequest request, HttpServletResponse response)   {
        return "html/about_us.html";
    }

    /**
     * 跳转到联系我们
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Fordword
    public String linkUs(HttpServletRequest request, HttpServletResponse response)   {
        return "html/link_us.html";
    }

    /**
     * 跳转到商城界面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Fordword
    public String shop(HttpServletRequest request, HttpServletResponse response)   {
        return "html/goods_list.html";
    }

    @Redirect
    public String login(HttpServletRequest request, HttpServletResponse response)   {
        return "jsp/customer_login.jsp";
    }
    @Redirect
    public String register(HttpServletRequest request, HttpServletResponse response)   {
        return "jsp/register.jsp";
    }

}
