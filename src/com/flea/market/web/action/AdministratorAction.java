package com.flea.market.web.action;

import cn.dsna.util.images.ValidateCode;
import com.flea.market.entity.Result;
import com.flea.market.pojo.*;
import com.flea.market.service.AdministratorService;
import com.flea.market.service.CustomerService;
import com.flea.market.util.*;
import net.sf.json.JSONObject;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhh
 * @time: 2019/3/6 14:39
 */

public class AdministratorAction {

    @Resource(name = "administratorService")
    private AdministratorService administratorService;
    @Resource(name = "customerService")
    private CustomerService customerService;

    private String name;
    private String password;
    private String captcha;
    private Integer goodId;
    private String img;

    /**
     * 管理员修改商品信息
     * @param request
     * @param response
     * @return 修改页面
     */
    @Fordword
    public String find(HttpServletRequest request, HttpServletResponse response) {
        Goods goods = administratorService.findGood(goodId);
        request.setAttribute("goods",goods);
        String customerName = customerService.findById(goods.getGoodsOwner()).getRealName();
        request.setAttribute("owner",customerName);
        return "jsp/admin/admin_edit.jsp";
    }


    @Fordword
    public String loginUI(HttpServletRequest request, HttpServletResponse response) {
        return "jsp/admin_login.jsp";
    }

    /**
     * 管理员登录的方法
     *
     * @param request  HttpServletRequest
     * @param response HttpServletRequest
     * @return json {"code":'', "target":'', "msg":''}
     */
    public String login(HttpServletRequest request, HttpServletResponse response) {



        //验证验证码
        if (!request.getSession().getAttribute("captcha").equals(captcha)) {
            Result result = new Result();
            result.setCode(404);
            result.setMsg("验证码有误");
            return JSONObject.fromObject(result).toString();
        }

        // 创建登录实体
        Administrator administrator = new Administrator();
        administrator.setLoginName(name);
        administrator.setPassword(password);

        // 执行登录业务
        Result<Administrator> result = administratorService.login(administrator);
        // 登录是否成功
        if (result.getCode() == 200) {
            // 在session中添加登录成功的用户实体
            request.getSession().setAttribute("admin", result.getTarget());
        }
        // 反馈登录后的结果
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 以流的形式反馈验证码图片
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     */
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCode code = new ValidateCode(150, 50, 4, 9);
        code.write(response.getOutputStream());
        request.getSession().setAttribute("captcha", code.getCode());
    }


    public String updatePassword(HttpServletRequest request, HttpServletResponse response) {
        Administrator administrator = getAdministrator(request);
        createAdministrator(administrator);
        Result<Administrator> result = administratorService.updatePwd(administrator);
        return JSONObject.fromObject(result).toString();
    }


    private Administrator getAdministrator(HttpServletRequest request) {
        Administrator administrator = (Administrator) request.getSession().getAttribute("admin");
        Administrator byId = administratorService.findById(administrator.getId());
        byId.setPassword(null);
        return byId;
    }


    private void createAdministrator(Administrator administrator) {
        administrator.setName(name);
        administrator.setPassword(password);
    }


    public String checkPwd(HttpServletRequest request, HttpServletResponse response) {
        Administrator administrator = (Administrator) request.getSession().getAttribute("admin");
        administrator.setPassword(password);
        Result<Administrator> result = administratorService.checkPwd(administrator);
        return JSONObject.fromObject(result).toString();
    }


    @Redirect
    public String exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("admin");
        return "administrator_loginUI.action";
    }
    @Fordword
    public String changePasswordUI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/admin/admin_change_password.jsp";
    }

    public String updateImg(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
