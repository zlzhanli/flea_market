package com.flea.market.util.email;

/**
 * @author LiuTianyou
 * @date 2019/3/6
 */

public interface EmailResultLinstener {
    /**
     * 邮件发送成功回调的函数
     * @param code
     */
    public void sendSuccessful(String code);

    /**
     * 邮件发送失败的回调函数
     */
    public void sendFaild();
}
