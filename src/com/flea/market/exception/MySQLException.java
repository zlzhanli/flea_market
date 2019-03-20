package com.flea.market.exception;

/**
 * @author com.karl lee
 * @Date 2019/2/28
 */
public class MySQLException  extends RuntimeException{
    public MySQLException() {
        super();
    }

    public MySQLException(String message) {
        super(message);
    }
}
