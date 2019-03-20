package com.flea.market.web.action;

import com.flea.market.service.ServiceFactory;
import com.flea.market.util.XMLParser;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author com.com.karl lee
 * @Date 2019/2/26
 */
public class ActionFactory {
    private static final Log log = LogFactory.getLog(ActionFactory.class);
    private Map<String, Object> map;
    private ServiceFactory serviceFactory;
    private String config = "config.xml";
    private String factoryName = "action";
    private static ActionFactory factory;

    private ActionFactory() {
        init();
    }

    private void init() {
        serviceFactory = ServiceFactory.createFactory();
        map = new XMLParser(config).parse(factoryName);
    }

    public Object create(String key) throws IllegalAccessException, InstantiationException {
        if (map.get(key) == null) {
            log.error(this.getClass().getPackage() + ":创建失败,请保证" + key + "存在");
            throw new NullPointerException(this.getClass().getPackage() + ":创建失败,请保证" + key + "存在");
        }
        Object o = map.get(key).getClass().newInstance();
        log.info(map.get(key) + " create");
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Resource annotation = f.getAnnotation(Resource.class);
            if (annotation == null) {
                continue;
            }
            String name = annotation.name();
            f.set(o, serviceFactory.create(name));
        }
        return o;
    }

    public static ActionFactory createFactory() {
        if (factory == null) {
            factory = new ActionFactory();
        }
        return factory;
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
}
