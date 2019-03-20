package com.flea.market.util;

import java.util.UUID;

/**
 * @author zl
 * @time 2019/3/7
 */
public class UUIDutil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
