package com.flea.market.util.email;

/**
 * @author LiuTianyou
 * @date 2019/3/6
 */
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.transform.UnmarshallerContext;
import com.flea.market.util.RandomCodeUtils;
import com.flea.market.util.enumbeans.MailType;

import javax.servlet.http.HttpSession;


public class EmailUtils {
    @SuppressWarnings("all")
    public String  sendCodeMail(String email, MailType type) {
        // 连接协议
                try {
                    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIfbijaRc3S1EI", "Z6TPBf8SYCph2aKEJZrkmhTYUhtC84");
                    IAcsClient client = new DefaultAcsClient(profile);
                    String code = RandomCodeUtils.getRandomCode(6);
                    SingleSendMailRequest request = new SingleSendMailRequest();
                    request.setAccountName("customerservice@www.simplepi.top");
                    request.setAddressType(1);
                    request.setTagName("findpwd");
                    request.setReplyToAddress(true);
                    request.setToAddress(email);
                    request.setSubject(type.getTitle());
                    request.setHtmlBody("您申请的验证码：【" + code + "】5分钟内有效,如非本人操作请忽略。");
                    SingleSendMailResponse httpResponse = client.getAcsResponse(request);
                    System.out.println(httpResponse);
                    System.out.println(httpResponse.getRequestId());
                    return code;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

}
