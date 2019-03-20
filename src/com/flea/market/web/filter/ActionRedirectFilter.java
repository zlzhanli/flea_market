package com.flea.market.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ActionRedirectFilter", urlPatterns = "*")
public class ActionRedirectFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ((HttpServletRequest) req).setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String uri = ((HttpServletRequest) req).getRequestURI();
        String[] paths = uri.split("/");
        if (paths.length == 0) {
            chain.doFilter(req, resp);
            return;
        }
        String path = paths[paths.length > 0 ? paths.length - 1 : 0];

//        if (!path.contains(".")) {
//            ((HttpServletResponse) resp).sendRedirect(uri.concat(".action"));
//            return;
//        }
        try{

            chain.doFilter(req, resp);
        }catch (Throwable throwable){
            PrintWriter out = resp.getWriter();
            out.println("系统发生了错误，请联系管理员");
            out.flush();
            out.close();
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
