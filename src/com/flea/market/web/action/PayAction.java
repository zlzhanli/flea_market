package com.flea.market.web.action;

import com.flea.market.pojo.Comment;
import com.flea.market.pojo.Customer;
import com.flea.market.service.CustomerService;
import com.flea.market.util.Fordword;
import com.flea.market.util.Redirect;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author LiuTianyou
 * @date 2019/3/17
 */

public class PayAction {
    String token;
    String codepay_id;

    String price;
    String type;
    String pay_id;
    String param;
    String notify_url;
    String return_url;
    @Resource(name="customerService")
    private CustomerService customerService;

    public String pay(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(price.equals("200")) {
            Customer customer = (Customer) request.getSession().getAttribute("customer");
            customer.setUserBalance(new BigDecimal(999999));
            customerService.update(customer);
        }
            token = "w6tTTLEhsElLmnXMUWt9lJxOWTU4DftU"; //记得更改 http://codepay.fateqq.com 后台可设置
            codepay_id ="48187" ;//记得更改 http://codepay.fateqq.com 后台可获得

            price=request.getParameter("price"); //表单提交的价格
            type="1";
            Customer customer= (Customer) request.getSession().getAttribute("customer"); //支付人的唯一标识
            pay_id=customer.getId().toString();
            param="1111"; //自定义一些参数 支付后返回
            notify_url="http://55.55.5.2:8080/codepay.jsp";//通知地址
            return_url="";//支付后同步跳转地址

            if(price==null){
                price="1";
            }
            //参数有中文则需要URL编码
            String url="http://codepay.fateqq.com:52888/creat_order?id="+codepay_id+"&pay_id="+pay_id+"&price="+price+"&type="+type+"&token="+token+"&param="+param+"&notify_url="+notify_url+"&return_url="+return_url;
            return  url;



    }
}
