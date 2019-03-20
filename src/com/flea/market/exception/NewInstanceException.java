package com.flea.market.exception;

/**
 * @author com.karl lee
 * @Date 2019/2/28
 */
public class NewInstanceException extends RuntimeException {
    public NewInstanceException() {
        super();
    }

    public NewInstanceException(String message) {
        super(message);
    }
}
