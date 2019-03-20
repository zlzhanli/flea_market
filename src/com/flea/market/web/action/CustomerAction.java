package com.flea.market.web.action;

import cn.dsna.util.images.ValidateCode;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.service.CustomerService;
import com.flea.market.util.*;
import com.flea.market.util.email.EmailResultLinstener;
import com.flea.market.util.email.EmailUtils;
import com.flea.market.util.enumbeans.MailType;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.font.EAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zl
 * @time 2019/3/6
 */
public class CustomerAction {
    @Resource(name = "customerService")
    private CustomerService customerService;

    private String captcha;
    private Integer id;
    private String head;

    private Date gmtModified;
    private BigDecimal userBalance;
    private Integer userStatus;
    private Date birthday;
    private String province;
    private String city;
    private String area;
    private String sex;
    private String email;
    private String phone;
    private String idCard;
    private String password;
    private String nickName;
    private String loginName;
    private String realName;
    private String verification;
    private Integer mailType;
    private String mailCode;
    private String yanzhenma;

    private Integer page;
    private Integer rows;

    /**
     * 获取用户集合的方法
     * @param request
     * @param response
     * @return
     */
    public String list(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        List<Customer> customerList = customerService.list();

        DetachedCriteria criteria = new DetachedCriteria();
        criteria.add("id",new DetachedCriteria.Greater<>(0));
        // 分页查询
        PageBean<Customer> pageBean = customerService.search(criteria, page, rows);
        // 查询到的商品记录
        map.put("rows", pageBean.getList());
        // 返回页面符合条件的总记录数
        map.put("total", pageBean.getTotalCount());
        map.put("current", pageBean.getCurrPage());

        return JSONUtil.toJson1(map).toString();
    }

    /**
     * 客户登录
     *
     * @param request
     * @param response
     * @return
     */
    public String login(HttpServletRequest request, HttpServletResponse response) {

        //验证验证码
        if (!request.getSession().getAttribute("captcha").toString().equalsIgnoreCase(captcha)) {
            Result result = new Result();
            result.setCode(404);
            result.setMsg("验证码有误");
            return JSONObject.fromObject(result).toString();
        }

        // 创建登录实体
        Customer customer = new Customer();
        customer.setLoginName(loginName);
        customer.setEmail(loginName);
        customer.setPhone(loginName);
        customer.setPassword(password);

        // 执行登录业务
        Result<Customer> result = customerService.login(customer);
        // 登录是否成功
        if (result.getCode() == 200) {
            // 在session中添加登录成功的用户实体
            request.getSession().setAttribute("customer", result.getTarget());

        }

        // 反馈登录后的结果
        return JSONUtil.toJson1(result).toString();
    }

    @Fordword
    public String loginUI(HttpServletRequest request, HttpServletResponse response) {
        return "jsp/customer_login.jsp";
    }

    @Fordword
    public String changePasswordUI(HttpServletRequest request, HttpServletResponse response) {
        return "jsp/customer/change_password.jsp";
    }

    public String info(HttpServletRequest request, HttpServletResponse response) {
        Customer byId = getCustomer(request);
        String idCard=byId.getIdCard();
        String phone=byId.getPhone();
        if(phone!=null){
            phone=phone.substring(0,3)+"****"+phone.substring(7,11);
            byId.setPhone(phone);
        }
        if(idCard!=null){


         idCard=idCard.substring(0,14)+"****";
         byId.setIdCard(idCard);
        }


        return JSONUtil.toJson1(byId).toString();
    }

    /**
     * 通过id获取用户信息的方法
     *
     * @param request
     * @return
     */
    private Customer getCustomer(HttpServletRequest request) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        Customer byId = customerService.findById(customer.getId());
        byId.setPassword(null);
        return byId;
    }

    public String updateHead(HttpServletRequest request, HttpServletResponse response) {

        head = head.replaceFirst("data:image/png;base64,", "");
        String newFileName = UUIDutil.getUUID() + ".jpg";
        String loadPath = request.getServletContext().getRealPath("/") + "upload/";
        String path = loadPath + newFileName;

        Base64Utils.Base64ToImage(head, path);
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setPhoto(newFileName);
        Result<Customer> result = customerService.updatePhoto(customer);
        return JSONUtil.toJson1(result).toString();
    }

    @Fordword
    public String infoUI(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("info", getCustomer(request));
        return "jsp/customer/customer_info_index.jsp";
    }


    @Fordword
    public String accountUI(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("info", getCustomer(request));
        return "jsp/customer/account.jsp";
    }

    @Redirect
    public String myForumUI(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }


    /**
     * 获取用户登录信息
     *
     * @param request
     * @param response
     * @return
     */
    public String findCustomer(HttpServletRequest request, HttpServletResponse response) {
        Object c = request.getSession().getAttribute("customer");
        return JSONObject.fromObject(c).toString();
    }
    /**
     * 根据id查找customer
     *
     * @param request
     * @param response
     * @return
     */
    public String findByIdCustomer(HttpServletRequest request, HttpServletResponse response) {
        Customer byId = customerService.findById(id);
        return JSONObject.fromObject(byId).toString();
    }

    /**
     * 注册时的save方法
     *
     * @param request
     * @param response
     * @return
     */
    public String save(HttpServletRequest request, HttpServletResponse response) {
        //验证验证码


        if (!request.getSession().getAttribute("captcha").toString().equalsIgnoreCase(verification)) {
            final int error = 404;
            Result result = new Result();
            result.setCode(error);
            result.setMsg("验证码有误");
            return JSONObject.fromObject(result).toString();
        }

        Customer customer = new Customer();
        customer.setLoginName(loginName);
        customer.setNickName(nickName);
        customer.setPassword(password);
        customer.setPhoto("head.png");
        Result<Customer> result = customerService.save(customer);
        // 注册成功
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 验证用户是否重复
     *
     * @param request
     * @param response
     * @return
     */
    public String findByLoginName(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = customerService.findByLoginName(loginName);
        // 从在此用户名
        if (customer != null) {
            return "400";
        }
        return "200";
    }


    @Deprecated
    public String findByLoginNameAndEmail(HttpServletRequest request, HttpServletResponse response){
        Customer customer = customerService.findByLoginNameAndEmail(loginName,email);
        String code = (String) request.getSession().getAttribute("mailCode");
        String mailAddress = (String) request.getSession().getAttribute("mailAddress");
        long mailCreateTime = (long) request.getSession().getAttribute("mailCodeCreateTime");
        //判断用户输入的验证码和存储的验证码

        if (!mailCode.trim().equals(code) || !mailAddress.trim().equals(email)) {

            return "500";
        }
        if (System.currentTimeMillis() - mailCreateTime > 1000 * 60 * 5) {
            return "301";
        }
        if(customer==null){
            return "404";
        }
        request.getSession().setAttribute("email",customer.getEmail());
        return "200";
    }


    public String findByEmail(HttpServletRequest request, HttpServletResponse response){
        Customer customer = customerService.findByEmail(request.getSession().getAttribute("email").toString());
        if(customer!=null){
            customer.setPassword(password);
            customerService.updatePwd(customer);
            return "200";
        }else {
            return "404";
        }
    }
    /**
     * 找回密码
     * @param request
     * @param response
     * @return
     */
    public String findBackPwd(HttpServletRequest request, HttpServletResponse response){
        Customer customer = customerService.findByLoginName(loginName);
        if(customer==null){
            return "404";
        }
        return "200";
    }

    /**
     * 修改customer信息
     *
     * @param request
     * @param response
     * @return
     */
    public String update(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = new Customer();
        createCustomer(customer);
        Result<Customer> result = customerService.update(customer);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 修改密码的方法
     *
     * @param request
     * @param response
     * @return
     */
    public String updatePassword(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = getCustomer(request);
        createCustomer(customer);
        Result<Customer> result = customerService.updatePwd(customer);
        return JSONObject.fromObject(result).toString();
    }
    @Fordword
    public String registerUI(HttpServletRequest request, HttpServletResponse response) {

        return "jsp/register.jsp";
    }

    /**
     * 修改手机号
     *
     * @param response
     * @param request
     * @return
     */
    public String updatePhone(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setPhone(phone);
        Result<Customer> result = customerService.updatePhone(customer);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 修改邮箱
     *
     * @param response
     * @param request
     * @return
     */
    public String updateEmail(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = ((Customer) request.getSession().getAttribute("customer"));
        customer.setEmail(email);
        Result<Customer> result = customerService.updateEmail(customer);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 修改身份证号
     *
     * @param response
     * @param request
     * @return
     */
    public String updateIdCard(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setIdCard(idCard);
        Result<Customer> result = customerService.updateIdCard(customer);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 检查用户密码的方法
     *
     * @param request
     * @param response
     * @return
     */
    public String checkPwd(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setPassword(password);
        Result<Customer> result = customerService.checkPwd(customer);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 创建customer
     *
     * @param customer
     */
    private void createCustomer(Customer customer) {
        customer.setGmtModified(gmtModified);
        customer.setUserBalance(userBalance);
        customer.setUserStatus(userStatus);
        customer.setBirthday(birthday);
        customer.setProvince(province);
        customer.setCity(city);
        customer.setArea(area);
        customer.setSex(sex);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setIdCard(idCard);
        customer.setPassword(password);
        customer.setNickName(nickName);
        customer.setLoginName(loginName);
        customer.setRealName(realName);
    }


    /**
     * 修改客户昵称
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return json {"code":'', "target":'', "msg":''}
     */
    public String updateNickName(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setNickName(nickName);
        Result<Customer> result = customerService.updateNickName(customer);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 修改客户昵称
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return json {"code":'', "target":'', "msg":''}
     */
    public String updateSex(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setSex(sex);
        Result<Customer> result = customerService.updateSex(customer);
        return JSONObject.fromObject(result).toString();
    }
    public String updateRealName(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setRealName(realName);
        Result<Customer> result = customerService.update(customer);
        return JSONObject.fromObject(result).toString();
    }


    /**
     * 验证码方法
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws IOException
     */
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCode code = new ValidateCode(150, 50, 4, 9);
        request.getSession().setAttribute("captcha", code.getCode());
        code.write(response.getOutputStream());
    }

    //用户绑定邮箱
    public String bindEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        Result result = new Result();
        String code = (String) request.getSession().getAttribute("mailCode");
        String mailAddress = (String) request.getSession().getAttribute("mailAddress");
        long mailCreateTime = (long) request.getSession().getAttribute("mailCodeCreateTime");
        //判断用户输入的验证码和存储的验证码
        if (!mailCode.trim().equals(code) || !mailAddress.trim().equals(email)) {
            result.setCode(304);
            result.setMsg("验证码无效请重新获取");
            return JSONObject.fromObject(result).toString();
        }
        if (System.currentTimeMillis() - mailCreateTime > 1000 * 60 * 5) {
            result.setCode(301);
            result.setMsg("验证码已失效，请重新获取");
            return JSONObject.fromObject(result).toString();
        }
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setEmail(mailAddress);

        result = customerService.updateEmail(customer);
        return JSONObject.fromObject(result).toString();
    }

    public String sendEmailCode(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        MailType type;
        PrintWriter pw = response.getWriter();
        final Integer[] exit = {0};
        switch (mailType) {
            case 1:
                type = MailType.BIND_EMAIL;
                break;
            case 2:
                type = MailType.UNBIND_EMAIL;
                break;
            case 3:
                type = MailType.FIND_PASSWORD;
                break;
            default:
                type = MailType.BIND_EMAIL;
        }
        String mailCode = new EmailUtils().sendCodeMail(email, type);
        //向session中存入邮件验证码
        request.getSession().setAttribute("mailCode", mailCode);
        //向session中存储产生验证码的时间
        request.getSession().setAttribute("mailCodeCreateTime", System.currentTimeMillis());
        //向session中存储邮件地址
        request.getSession().setAttribute("mailAddress", email);
        if (null != mailCode) {
            return "200";
        } else {
            return "100";
        }
    }


    //下载文件
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取要下载的文件名
        String filename = "com.flea_market.apk";
        //得到想客服端输出的输出流
        OutputStream outputStream = response.getOutputStream();
        //输出文件用的字节数组，每次向输出流发送600个字节
        byte b[] = new byte[600];
        //要下载的文件
        File fileload = new File(request.getRealPath("/") + "static/local/file", "com.flea_market.apk");
        //客服端使用保存文件的对话框
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        //通知客服文件的MIME类型
        response.setContentType("application/vnd.android.package-archive");
        //通知客服文件的长度
        long fileLength = fileload.length();
        String length = String.valueOf(fileLength);
        response.setHeader("Content_length", length);
        //读取文件，并发送给客服端下载
        FileInputStream inputStream = new FileInputStream(fileload);
        int n = 0;
        while ((n = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, n);
        }
    }

    public String getCustomerInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map map = new HashMap();
        Customer c = (Customer) request.getSession().getAttribute("customer");
        if (null == c) {
            map.put("msg", "error");
            return JSONObject.fromObject(map).toString();
        }
        c = customerService.findById(c.getId());
        map.put("msg", "ok");
        map.put("id", c.getId());
        map.put("nickName", c.getNickName());
        map.put("loginName", c.getLoginName());
        map.put("photo", c.getPhoto());
        return JSONObject.fromObject(map).toString();
    }

    @Fordword
    public String exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("customer");
        return "html/index.html";
    }

}
