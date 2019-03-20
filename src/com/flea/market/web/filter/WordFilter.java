package com.flea.market.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter(filterName = "JspFilter", urlPatterns = "*")
public class WordFilter implements Filter {
    List<String> wordList = new ArrayList<String>();

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //用户提交的数据是否包含禁用词
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String value = request.getParameter((String) e.nextElement());
            for (String regex : wordList) {
                //正则表达式判断用户输入的内如是否存在
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(value);
                if (m.find()) {
                    request.setAttribute("message", "你发表的文章中含有非法词汇，你懂的！！");
                    request.getRequestDispatcher("message.jsp").forward(request, response);
                    return;
                }
            }
        }
        arg2.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        //初始化 将敏感词存进集合

        wordList.add("法轮功");
        wordList.add("猪");
        wordList.add("傻逼");
        wordList.add("傻子");
        wordList.add("傻屌");
        wordList.add("天安门事件");
        wordList.add("自由亚州");
        wordList.add("无界浏览");
        wordList.add("美国之音");
        wordList.add("退党");
        wordList.add("明慧网");
        wordList.add("明慧");
        wordList.add("九评共产党");

    }

}
