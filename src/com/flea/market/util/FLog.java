package com.flea.market.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author karl lee
 * @Date 2019/3/11
 */
public class FLog {
    private static final Log log = LogFactory.getLog(FLog.class);

    public static void info(Object info) {
        log.info(info);
    }

    public static void info(Object info, Throwable throwable) {
        log.info(info, throwable);
    }

    public static void error(Object info, Throwable throwable) {
        log.error(info, throwable);
    }

    public static void error(Object info) {
        log.error(info);
    }

    public static void warn(Object info, Throwable throwable) {
        log.warn(info, throwable);
    }

    public static void warn(Object info) {
        log.warn(info);

    }

}
