package com.flea.market.dao.base;


import com.flea.market.util.XMLParser;

import java.util.Map;

/**
 * @author com.com.karl lee
 * @Date 2019/2/26
 */
public class DAOFactory {
    private Map<String, Object> map;
    private static DAOFactory factory;
    private String config = "config.xml";
    private String factoryName = "dao";

    private DAOFactory() {
        init();
    }

    private void init() {
        XMLParser parser = new XMLParser(config);
        map = parser.parse(factoryName);
    }

    public Object create(String key) {
        return map.get(key);
    }

    public static DAOFactory createFactory() {
        if (factory == null) {
            factory = new DAOFactory();
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
