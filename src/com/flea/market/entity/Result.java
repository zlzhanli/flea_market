package com.flea.market.entity;

import java.io.Serializable;

/**
 * @author com.karl lee
 * @Date 2019/2/27
 */
public class Result<T> implements Serializable {
    private Integer code;
    private T target;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", target=" + target +
                ", msg='" + msg + '\'' +
                '}';
    }
}
