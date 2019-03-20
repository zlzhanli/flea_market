package com.flea.market.service;


import com.flea.market.dao.base.DAOFactory;
import com.flea.market.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author com.karl lee
 * @Date 2019/2/28
 */
public class ServiceFactory {
    private static final Log log = LogFactory.getLog(ServiceFactory.class);
    private Map<String, Object> map;
    private DAOFactory daoFactory;
    private String config = "config.xml";
    private String factoryName = "service";
    private static ServiceFactory factory;

    private ServiceFactory() {
        init();
    }

    private void init() {
        daoFactory = DAOFactory.createFactory();
        map = new XMLParser(config).parse(factoryName);
        initProxy();
    }

    private void initProxy() {
        for (String key : map.keySet()) {
            Object o = map.get(key);
            map.put(key, create(o));
        }
    }

    public static ServiceFactory createFactory() {
        if (factory == null) {
            factory = new ServiceFactory();
        }
        return factory;
    }

    public Object create(String key) {
        return map.get(key);
    }

    private Object create(Object o) {
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Resource annotation = field.getAnnotation(Resource.class);
            if (annotation == null) {
                continue;
            }
            String name = annotation.name();
            try {
                field.set(o, daoFactory.create(name));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("ResourceLoadException:Attribute loading failed at name=" + name);
            }
        }
        return proxy(o);
    }


    private Object proxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new Handler(target));
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    class Handler implements InvocationHandler {
        private Object target;

        public Handler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getParameterCount() > 0 && isNull(args)) {
                throw new NullPointerException(method.getName() + "执行时参数不能为空");
            }
            if (method.getAnnotation(AutoCommit.class) == null) {
                return method.invoke(target, args);
            }
            DbUtil.getConn().setAutoCommit(false);
            Object result = null;
            try {
                result = method.invoke(target, args);
                DbUtil.getConn().commit();
                log.info("database transaction commit...");
            } catch (Throwable throwable) {
                DbUtil.getConn().rollback();
                log.info("database transaction rollback...");
                close(method);
                throw throwable;
            }
            close(method);
            return result;
        }

        private boolean isNull(Object[] args) {
            for (Object o : args) {
                if (o == null) {
                    return true;
                }
            }
            return false;
        }

        private void close(Method method) {
            if (method.getAnnotation(AutoClose.class) == null) {
                return;
            }
            try {
                if (!DbUtil.getConn().isClosed()) {
                    DbUtil.close();
                    log.info("database connection close....");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
