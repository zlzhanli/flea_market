package com.flea.market.service.impl;

import com.flea.market.dao.CustomerDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.CardNumberInfo;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.service.CustomerService;
import com.flea.market.util.CardNumUtils;
import net.sf.json.JSONArray;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/6
 */
public class CustomerServiceImpl implements CustomerService {

    @Resource(name = "customerDAO")
    private CustomerDAO customerDAO;

    /**
     * 根据id查找customer
     *
     * @param id
     * @return
     */
    @Override
    public Customer findById(Integer id) {
        return customerDAO.findById(id);
    }

    @Override
    public PageBean<Customer> search(DetachedCriteria criteria, Integer page, Integer rows) {
        PageBean<Customer> pageBean = new PageBean<>();
        // 设置当前页数
        Integer currPage = page;
        Integer pageSize = rows;
        pageBean.setCurrPage(currPage);
        System.out.println(pageBean.getCurrPage()+"_________________________当前页___________");
        // 设置每页的记录数
        pageBean.setPageSize(pageSize);
        System.out.println(pageBean.getPageSize()+"_________________________每页显示条数___________");
        // 设置总记录数

        Integer totalCount = count(new DetachedCriteria());
        pageBean.setTotalCount(totalCount);
        System.out.println(pageBean.getTotalCount()+"______________________总记录数______________");
        // 设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc / pageSize);
        pageBean.setTotalPage(num.intValue());
        System.out.println(pageBean.getTotalPage()+"______________________总ye数2______________");
        // 设置每页显示的数据集合
        Integer begin = (currPage - 1) * pageSize;
        System.out.println(begin+"______________________开始______________");
        List<Customer> customerList = customerDAO.listByPage(criteria, begin, pageSize);

        pageBean.setList(customerList);
        return pageBean;
    }

    @Override
    public Integer count(DetachedCriteria detachedCriteria) {
        return customerDAO.count(new DetachedCriteria());
    }

    @Override
    public Result<Customer> save(Customer customer) {
        Result<Customer> result = new Result<>();
        String pwd = DigestUtils.md5Hex(customer.getPassword());
        customer.setPassword(pwd);
        customer.setUserBalance(new BigDecimal(0));
        customer.setUserStatus(1);
        customer.setGmtCreate(new Date());
        customerDAO.save(customer);
        if (customer.getId() == null) {
            result.setCode(500);
            result.setMsg("系统异常：注册失败");
            return result;
        }
        result.setCode(200);
        result.setMsg("注册成功");
        return result;
    }

    /**
     * 根据loginName查customer中的login_name
     * @param loginName
     * @return
     */
    @Override
    public Customer findByLoginName(String loginName) {
        return customerDAO.findByLoginName(loginName);
    }

    /**
     * 修改customer信息
     *
     * @param customerVo
     * @return
     */
    @Override
    public Result<Customer> update(Customer customerVo) {
        Result<Customer> result = new Result<>();
        try {
            Customer customerDo = customerDAO.findById(customerVo.getId());
            if (customerVo.getGmtModified() != null) {
                customerDo.setGmtModified(customerVo.getGmtModified());
            }
            if (customerVo.getUserBalance() != null) {
                customerDo.setUserBalance(customerVo.getUserBalance());
            }
            if (customerVo.getUserStatus() != null) {
                customerDo.setUserStatus(customerVo.getUserStatus());
            }
            if (customerVo.getBirthday() != null) {
                customerDo.setBirthday(customerVo.getBirthday());
            }
            if (customerVo.getProvince() != null) {
                customerDo.setProvince(customerVo.getProvince());
            }
            if (customerVo.getCity() != null) {
                customerDo.setCity(customerVo.getCity());
            }
            if (customerVo.getArea() != null) {
                customerDo.setArea(customerVo.getArea());
            }
            if (customerVo.getSex() != null) {
                customerDo.setSex(customerVo.getSex());
            }
            if (customerVo.getEmail() != null) {
                customerDo.setEmail(customerVo.getEmail());
            }
            if (customerVo.getPhone() != null) {
                customerDo.setPhone(customerVo.getPhone());
            }
            if (customerVo.getNickName() != null) {
                customerDo.setNickName(customerVo.getNickName());
            }
            if (customerVo.getRealName() != null) {
                customerDo.setRealName(customerVo.getRealName());
            }

            customerDo.setPhoto(customerVo.getPhoto());
            customerDAO.update(customerDo);
            result.setCode(200);
            result.setMsg("修改成功");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("修改失败！");
            e.printStackTrace();
            throw new RuntimeException("修改失败" + e.getMessage());
        }
        return result;
    }

    /**
     * 修改手机号
     *
     * @param customerPo
     * @return
     */
    @Override
    public Result<Customer> updatePhone(Customer customerPo) {
        Result<Customer> result = new Result<>();
        try {
            Customer customerDo = customerDAO.findById(customerPo.getId());
            customerDo.setPhone(customerPo.getPhone());
            customerDAO.update(customerDo);
            result.setCode(200);
            result.setMsg("修改电话成功！");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("修改失败");
            throw new RuntimeException("修改失败" + e.getMessage());
        }
        return result;
    }

    /**
     * 修改邮箱
     *
     * @param customerEo
     * @return
     */
    @Override
    public Result<Customer> updateEmail(Customer customerEo) {
        Result<Customer> result = new Result<>();
        try {
            Customer customerDo = customerDAO.findById(customerEo.getId());
            customerDo.setEmail(customerEo.getEmail());
            customerDAO.update(customerDo);
            result.setCode(200);
            result.setMsg("修改邮箱成功！");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("修改失败");
            throw new RuntimeException("修改失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public Customer findByLoginNameAndEmail(String loginName, String email) {

        return customerDAO.findByLoginNameAndEmail(loginName,email);

    }

    @Override
    public Customer findByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    /**
     * 修改身份证号
     *
     * @param customerIo
     * @return
     */
    @Override
    public Result<Customer> updateIdCard(Customer customerIo) {
        Result<Customer> result = new Result<>();
        try {
            Customer customerDo = customerDAO.findById(customerIo.getId());
            Result<CardNumberInfo> tem=CardNumUtils.checkCardNum(customerIo.getIdCard());
            if(tem.getCode()==200){
                customerDo.setIdCard(customerIo.getIdCard());
                customerDo.setSex(tem.getTarget().getSex());
                customerDo.setArea(tem.getTarget().getArea());
                String str=customerIo.getIdCard().substring(6,14);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

                customerDo.setBirthday(sdf.parse(str));
                //TODO 显示生日
                customerDAO.update(customerDo);
                result.setCode(200);
                result.setMsg("修改身份证号成功！");
                result.setTarget(null);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("修改失败");
            throw new RuntimeException (e);
          //  throw new RuntimeException("修改失败" + e.getMessage());
        }
        return result;
    }

    /**
     * 修改头像信息
     * @param customerPt 获取修改的头像信息以及客户id
     * @return
     */
    @Override
    public Result<Customer> updatePhoto(Customer customerPt) {
        Result<Customer> result = new Result<>();
        try {
            Customer customerDo = customerDAO.findById(customerPt.getId());
            customerDo.setPhoto(customerPt.getPhoto());
            customerDAO.updatePhoto(customerDo);
            result.setCode(200);
            result.setMsg("修改头像成功！");
        }catch (Exception e){
            result.setCode(500);
            result.setMsg("修改失败");
            throw new RuntimeException("修改失败"+e.getMessage());
        }
        return  result;
    }

    @Override
    public Result<Customer> updateNickName(Customer customerVo) {
        Result<Customer> result = new Result<>();
        try {
            // 查询需要修改的持久化对象
            Customer customerDo = customerDAO.findById(customerVo.getId());
            // 修改需要的数据
            customerDo.setNickName(customerVo.getNickName());
            // 保存修改的数据
            customerDAO.updateNickName(customerDo);
            result.setCode(200);
            result.setMsg("修改昵称成功！");
        }catch (Exception e){
            result.setCode(500);
            result.setMsg("修改失败");
            throw new RuntimeException("修改失败"+e.getMessage());
        }
        return  result;
    }

    @Override
    public Result<Customer> checkPwd(Customer customerPo) {
        Result<Customer> result = new Result<>();
        //根据id获取数据库中客户的原数据
        Customer customerDo = customerDAO.findById(customerPo.getId());
        //将用户上传的旧密码加密
        String pwdPo = DigestUtils.md5Hex(customerPo.getPassword());
        //验证新旧密码是否一致
        if (customerDo.getPassword().equals(pwdPo)){
            //一致的话，返回一个code值，提示用户可以输入新密码
            result.setCode(200);
        }else {
            //返回错误信息，此时用户不能提交新密码
            result.setCode(500);
            result.setMsg("输入的密码与原密码不一致！");
        }
        return result;

    }

    @Override
    public Result<Customer> updatePwd(Customer customerPo) {
        Result<Customer> result = new Result<>();
        try {
            //通过id获取客户的信息
            Customer customerDo = customerDAO.findById(customerPo.getId());
            //通过加密设置新的密码
            customerDo.setPassword(DigestUtils.md5Hex(customerPo.getPassword()));
            //修改原密码
            customerDAO.updatePwd(customerDo);
            result.setCode(200);
            result.setMsg("修改密码成功！");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("修改失败");
            throw new RuntimeException("修改失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Customer> login(Customer customerVo) {
        Result<Customer> result = new Result<>();

        // 获取符合条件的数据记录
        Customer customerDo = null;
        try {
            customerDo = customerDAO.findByLoginNameOrPhoneOrEmail(customerVo.getLoginName(),
                    customerVo.getEmail(), customerVo.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("系统异常");
            throw new RuntimeException("CustomerLoginException: 用户登录查询数据库时出现异常，请保证" +
                    "查询需要的条件符合规定" + e.getMessage());

        }
        // 没有符合的记录
        if (customerDo == null) {
            result.setMsg("该用户不存在！");
            result.setCode(404);
            return result;
        }
        //md5加密
        String passwordVo = DigestUtils.md5Hex(customerVo.getPassword());
        // 对密码是否正确
        if (customerDo.getPassword().equals(passwordVo)) {
            result.setCode(200);
            result.setTarget(customerDo);
        } else {
            result.setCode(500);
            result.setMsg("密码错误！");
        }
        return result;
    }

    @Override
    public Result<Customer> updateSex(Customer customer) {
        Result<Customer> result = new Result<>();
        try {
            // 查询需要修改的持久化对象
            Customer customerDo = customerDAO.findById(customer.getId());
            // 修改需要的数据
            customerDo.setSex(customer.getSex());
            // 保存修改的数据
            customerDAO.update(customerDo);

            result.setCode(200);
            result.setMsg("修改性别成功！");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("修改失败");
            throw new RuntimeException("修改失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Customer> list() {
        return customerDAO.list();
    }
}
