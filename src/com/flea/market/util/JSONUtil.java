package com.flea.market.util;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @author karl lee
 * @Date 2019/3/8
 */
public class JSONUtil {
    /**
     * 将一个对象直接转换为一个JSONObject对象，
     * 同样适合于JSON格式的字符串
     * 但是如果存在java.sql.Date或者java.sql.Timestamp时间格式，调用例外一个toJson转换方法
     *
     * @param obj
     * @return
     */
    public static JSONObject toJson(Object obj) {
        return JSONObject.fromObject(obj);
    }

    /**
     * @param obj        需要转换的参数
     * @param processors 类型转换器的集合,参数是一个Map集合，键代表需要转换类型的全路径，值是类型转换器
     * @return
     * @throws ClassNotFoundException
     */
    public static JSONObject toJson(Object obj, Map<String, JsonValueProcessor> processors) throws ClassNotFoundException {
        //定义一个JSONConfig对象，该对象可以制定一个转换规则
        JsonConfig config = new JsonConfig();
        if (processors != null && !processors.isEmpty()) {
            Iterator<java.util.Map.Entry<String, JsonValueProcessor>> it = processors.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<java.lang.String, net.sf.json.processors.JsonValueProcessor> entry = (Map.Entry<java.lang.String, net.sf.json.processors.JsonValueProcessor>) it
                        .next();
                String key = entry.getKey();
                JsonValueProcessor processor = processors.get(key);
                //反射获取到需要转换的类型
                Class<?> cls = Class.forName(key);
                config.registerJsonValueProcessor(cls, processor);
            }
        }
        return JSONObject.fromObject(obj, config);
    }

    /**
     * 将对象转化为json 支持日期类型(util.date)的转换
     * @param obj 转换的对象
     * @return 转换好的json对象
     */
    public static JSONObject toJson1(Object obj) {
        Map<String, JsonValueProcessor> processors = new HashMap<String, JsonValueProcessor>();

        processors.put("java.util.Date", new UtilDateProcessor("yyyy-MM-dd hh:mm:ss"));
        try {
            return toJson(obj, processors);
        } catch (ClassNotFoundException e) {
            processors.put("java.util.Date", new UtilDateProcessor("yyyy-MM-dd"));
            processors.clear();
            try {
                return toJson(obj, processors);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return toJson(obj);

    }

    /**
     * 将对象转化为json 支持日期类型(sql.date)的转换
     * @param obj 转换的对象
     * @return 转换好的json对象
     */
    public static JSONObject toJson2(Object obj) {
        Map<String, JsonValueProcessor> processors = new HashMap<String, JsonValueProcessor>();

        processors.put("java.sql.Date", new SQLDateProcessor("yyyy-MM-dd hh:mm:ss"));
        try {
            return toJson(obj, processors);
        } catch (ClassNotFoundException e) {
            processors.put("java.sql.Date", new SQLDateProcessor("yyyy-MM-dd"));
            processors.clear();
            try {
                return toJson(obj, processors);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return toJson(obj);

    }
}

