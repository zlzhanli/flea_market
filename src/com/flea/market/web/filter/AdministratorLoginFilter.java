package com.flea.market.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: zhh
 * @time: 2019/3/6 21:17
 */

public class AdministratorLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        //X-Requested-With: XMLHttpRequest
        final String HEADINFO = "X-Requested-With";
        String ASY_HEAD = request.getHeader(HEADINFO);
        if (session.getAttribute("admin") == null) {
            // 判断请求是否为异步
            if ("XMLHttpRequest".equals(ASY_HEAD)) {
                // 源请求地址
                String sourceUrl = request.getHeader("Referer");
                session.setAttribute("actionConfig", sourceUrl);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(100);
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
                response.sendRedirect(request.getContextPath() + "administrator_loginUI.action");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
