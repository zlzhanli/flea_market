package com.flea.market.util.enumbeans;

/**
 * @author LiuTianyou
 * @date 2019/3/6
 */
public enum MailType {
    /**
     * 找回密码
     */
    FIND_PASSWORD("【 跳蚤市场 】找回密码安全验证"),
    /**
     * 绑定邮箱
     */
    BIND_EMAIL("【 跳蚤市场 】绑定邮箱安全验证"),
    /**
     * 解绑邮箱
     */
    UNBIND_EMAIL("【 跳蚤市场 】解绑邮箱安全验证");
    private String title;

    MailType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
