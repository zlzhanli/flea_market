package com.flea.market.util;



import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @author karl lee
 * @Date 2019/3/8
 */

public class SQLDateProcessor implements JsonValueProcessor{

    private String format = "yyyy-MM-dd hh:mm:ss";//自定义时间格式化的样式
    public SQLDateProcessor() {
        super();

    }

    public SQLDateProcessor(String format) {
        this.format = format;
    }

    public Object processArrayValue(Object arg0, JsonConfig arg1) {

        return arg0;
    }
    /**
     * 处理对象的值
     * str 这个参数是当前需要处理的属性名
     */
    public Object processObjectValue(String str, Object obj, JsonConfig arg2) {
        String ret = "";
        if(obj instanceof java.sql.Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            ret = sdf.format(new Date(((java.sql.Date) obj).getTime()));
        }
        return ret;
    }

}

