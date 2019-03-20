package com.flea.market.entity;


import com.flea.market.util.CardNumUtils;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author LiuTianyou
 * @date 2019/3/8
 */

public class CareNumResult  implements Serializable {
    String msg;
    CardNumberInfo result;
    String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CardNumberInfo getResult() {
        return result;
    }

    public void setResult(CardNumberInfo result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    @Override
    public String toString() {
        return "CareNumResult{" +
                "msg='" + msg + '\'' +
                ", result=" + result +
                ", retCode='" + retCode + '\'' +
                '}';
    }

    public static void main(String[] args){
        try {
            CardNumUtils.checkCardNum("51052120200508797x");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
