package com.flea.market.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "JspFilter", urlPatterns = "/jsp*")
public class JspFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("非法访问");
        out.flush();
        out.close();
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
