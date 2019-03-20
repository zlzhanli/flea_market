package com.flea.market.web.filter;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: zhh
 * @time: 2019/3/6 21:17
 */
public class CustomerLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截器过滤了你的请求：-----------------------");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        //X-Requested-With: XMLHttpRequest
        final String HEADINFO = "X-Requested-With";
        String ASY_HEAD = request.getHeader(HEADINFO);
        if (session.getAttribute("customer") == null) {
            // 判断请求是否为异步
            if ("XMLHttpRequest".equals(ASY_HEAD)) {
                // 源请求地址
                String sourceUrl = request.getHeader("Referer");
                session.setAttribute("actionConfig", sourceUrl);
                System.out.println("链接："+sourceUrl);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("6666");
                out.flush();
                out.close();
                return;
            } else {//没有登录，但请求方式为同步
                //目标请求地址
                StringBuffer tragetUrl = request.getRequestURL();
                //请求参数
                String requesParam = request.getQueryString();
                if (requesParam != null) {
                    tragetUrl.append("?").append(requesParam);
                }
                session.setAttribute("actionConfig", tragetUrl.toString());
                response.sendRedirect(request.getContextPath() + "/jsp/customer_login.jsp");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
