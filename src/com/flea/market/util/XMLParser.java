package com.flea.market.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * @author LiuTianyou
 * @date 2019/2/28
 */

public class XMLParser {
    private static final Log log = LogFactory.getLog(XMLParser.class);
    private Map<String, Object> map;
    private String path;

    public XMLParser(String path) {
        map = new HashMap<>();
        this.path = path;
    }

    public Map<String, Object> parse(String factory) {
        try {
            File f = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(path)).getPath());
            // 获取根节点
            Element root = new SAXReader().read(f).getRootElement();
            for (Iterator factoryElement = root.elementIterator("factory"); factoryElement.hasNext(); ) {
                Element parent = (Element) factoryElement.next();
                if (parent.attribute("name").getData().equals(factory)) {
                    System.out.println("factory create :" + factory);
                    for (Iterator beanElement = parent.elementIterator("bean"); beanElement.hasNext(); ) {
                        Element foo = (Element) beanElement.next();
                        String name = foo.elementText("bean-name");
                        String packagePath = foo.elementText("bean-class");
                        Class clazz = Class.forName(packagePath);
                        log.info("create: " + name + " : " + clazz);
                        map.put(name, clazz.newInstance());
                    }
                    return map;
                }
            }
        } catch (DocumentException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

}
