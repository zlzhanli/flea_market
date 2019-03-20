package com.flea.market.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zl
 * @time 2019/3/11
 */
public class SnUtil {

    static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getOrderSn(){
        return sdf.format(new Date());
    }

}
