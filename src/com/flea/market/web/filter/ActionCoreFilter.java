package com.flea.market.web.filter;

import com.flea.market.util.FLog;
import com.flea.market.util.Fordword;
import com.flea.market.util.Redirect;
import com.flea.market.web.action.ActionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebFilter(filterName = "actionCoreFilter")
public class ActionCoreFilter implements Filter {
    private static final Log log = LogFactory.getLog(ActionCoreFilter.class);
    private final String EXTENSION_NAME = ".action";
    private final Integer CLASS_CODE;
    private ActionFactory actionFactory;

    public ActionCoreFilter() {
        CLASS_CODE = 0;
    }


    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        // 解析路径
        log.info(((HttpServletRequest) req).getRequestURI());
        ((HttpServletRequest) req).setCharacterEncoding("utf-8");

        ActionPath path = new ActionPath(((HttpServletRequest) req).getRequestURI());

        try {
            // 获取访问的action对象
            Object action = actionFactory.create(path.getClassName());


            // 执行方法
            String text = null;
            Method method = null;
            synchronized (action) {
                // 为action属性赋值
                createFieldsValue(req, action);
                log.info("执行方法：" + path.getMethodName());

                // 获取执行的目标方法
                method = action.getClass().getMethod(path.getMethodName(),
                        HttpServletRequest.class, HttpServletResponse.class);

                text = (String) method.invoke(action, req, resp);
            }

            // 分发转向
            run(req, resp, method, text);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            log.error("访问指定类" + path.getClassName() + "中方法" + path.getMethodName() + "时，找不到指定的方法", e);
            write(resp, "系统没有找到终指定的资源！");
        } catch (IllegalAccessException e) {
            log.error("执行指定类" + path.getClassName() + "中方法" + path.getMethodName() + "时，没有足够权限，该方法可能是私有的（private）", e);
            e.printStackTrace();
            write(resp, "系统没有足够的权限访问，请联系管理员！");
        } catch (InvocationTargetException e) {
            log.error("执行指定类" + path.getClassName() + "中方法" + path.getMethodName() + "时，方法内部发生了错误，请检查方法内容", e);
            e.printStackTrace();
            write(resp, "系统发生错误，请稍后再试吧！");
        } catch (InstantiationException e) {
            log.error("创建指定类" + path.getClassName() + "时，不能实例化该实例，请检查该类是否存在无参构造", e);
            e.printStackTrace();
            write(resp, "系统发生未知错误，请联系管理员！");
        }

    }

    private void run(ServletRequest req, ServletResponse resp, Method method, String text) throws IOException, ServletException {
        // 重定向
        Redirect redirect = method.getAnnotation(Redirect.class);
        if (redirect != null) {
            ((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() + '/' + text);
            return;
        }
        // 转发
        Fordword fordword = method.getAnnotation(Fordword.class);
        if (fordword != null) {
            req.getRequestDispatcher(((HttpServletRequest) req).getContextPath() + '/' + text).forward(req, resp);
            return;
        }
        // 返回值为null时
        if (text == null) {
            return;
        }
        // 返回值为字符串时将字符写入流中
        write(resp, text);
    }
    private void createFieldsValue(ServletRequest req, Object action) {
        Field[] declaredFields = action.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            String value = req.getParameter(declaredFields[i].getName());
            if (value != null && !"".equals(value)) {
                try {
                    createField(action, declaredFields[i], value);
                } catch (IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void write(ServletResponse resp, String test) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(test);
        writer.flush();
        writer.close();
    }

    private boolean createField(Object action, Field declaredField, String value) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        declaredField.setAccessible(true);
        Class<?> type = declaredField.getType();
        if (type.equals(String.class)) {
            declaredField.set(action, value);
        } else if (type.equals(Integer.class)) {
            declaredField.set(action, Integer.parseInt(value));
        } else if (type.equals(Double.class)) {
            declaredField.set(action, Double.parseDouble(value));
        } else if (type.equals(Float.class)) {
            declaredField.set(action, Float.parseFloat(value));
        } else if (type.equals(Byte.class)) {
            declaredField.set(action, Byte.parseByte(value));
        } else if (type.equals(Boolean.class)) {
            declaredField.set(action, Boolean.parseBoolean(value));
        } else if (type.equals(Short.class)) {
            declaredField.set(action, Short.parseShort(value));
        } else if (type.equals(Date.class)) {
            declaredField.set(action, getDate(value));
        } else if (type.equals(BigDecimal.class)) {
            declaredField.set(action, new BigDecimal(value));
        } else {
            return false;
        }
        return true;
    }

    public static Object iocObj(Object o, Object value) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        Class<?> clazz = o.getClass();
        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getDeclaredFields();
        for (Method m : methods) {
            if (m.getName().startsWith("set")) {
                String fieldName = m.getName().substring(3).toLowerCase();
                if (fieldIsExits(fields, fieldName)) {
                    m.invoke(o, value);
                }
            }
        }

        return o;
    }

    private static boolean fieldIsExits(Field[] fields, String fieldName) {
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return true;
            }
        }
        return false;

    }

    private Date getDate(String strDate) {
        Date date = null;
        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //必须捕获异常
        try {
            date = simpleDateFormat.parse(strDate.trim());

        } catch (ParseException px) {
            //加上时间
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = simpleDateFormat.parse(strDate.trim());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            px.printStackTrace();
        }
        return date;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        actionFactory = ActionFactory.createFactory();
        log.info("actionFactory create success");

    }

    class ActionPath {
        private String path;
        private String className;
        private String methodName;

        public ActionPath(String path) {
            this.path = path;
            init();
        }

        private void init() {
            String NULL = "";
            String BEGIN_PATH_CODE = "/";
            String SEPARATOR = "_";
            Integer METHOD_CODE = 1;

            path = path.replace(EXTENSION_NAME, NULL);
            path = path.replaceFirst(BEGIN_PATH_CODE, NULL);
            String[] s = path.split(SEPARATOR);
            className = s[CLASS_CODE];
            methodName = s[METHOD_CODE];
        }

        public String getClassName() {
            return className;
        }

        public String getMethodName() {
            return methodName;
        }
    }

}
