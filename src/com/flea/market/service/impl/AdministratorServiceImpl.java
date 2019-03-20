package com.flea.market.service.impl;

import com.flea.market.dao.*;
import com.flea.market.entity.Result;
import com.flea.market.pojo.*;
import com.flea.market.service.AdministratorService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;

/**
 * @author: zhh
 * @time: 2019/3/6 13:55
 */

public class AdministratorServiceImpl implements AdministratorService {

    @Resource(name = "administratorDAO")
    private AdministratorDAO administratorDAO;
    @Resource(name = "goodsDAO")
    private GoodsDAO goodsDAO;

    @Override
    public Result<Administrator> login(Administrator administratorVo) {
        Result<Administrator> result = new Result<>();

        Administrator administratorDo = null;
        // 根据登录名查询符合的用户
        try {
            administratorDo = administratorDAO.findByLoginName(administratorVo.getLoginName());
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("系统异常");
            e.printStackTrace();
            throw new RuntimeException("AdminLoginDAOException: 管理员登录异常，" +
                    "在查询管理员记录时发生的异常，需保证管理员登录名和密码合法" + e.getMessage());
        }

        // 没有符合的记录
        if (administratorDo == null) {
            result.setMsg("该用户不存在");
            result.setCode(404);
            return result;
        }

        //md5加密
        String passwordVo = DigestUtils.md5Hex(administratorVo.getPassword());

        // 对密码是否正确
        if (administratorDo.getPassword().equals(passwordVo)) {

            result.setCode(200);
            result.setTarget(administratorDo);
            result.setMsg("登录成功！");
        } else {
            result.setMsg("密码错误！");
            result.setCode(505);
        }
        return result;
    }

    @Override
    public Result<Administrator> checkPwd(Administrator administratorPo) {
        Result<Administrator> result = new Result<>();
        //根据id获取数据库中客户的原数据
        Administrator administratorDo = administratorDAO.findById(administratorPo.getId());
        //将用户上传的旧密码加密
        String pwdPo = DigestUtils.md5Hex(administratorPo.getPassword());
        //验证新旧密码是否一致
        if (administratorDo.getPassword().equals(pwdPo)) {
            //一致的话，返回一个code值，提示用户可以输入新密码
            result.setCode(200);
        } else {
            //返回错误信息，此时用户不能提交新密码
            result.setCode(500);
            result.setMsg("输入的密码与原密码不一致！");
        }
        return result;

    }


    @Override
    public Goods findGood(Integer id) {
        return goodsDAO.findById(id);
    }

    @Override
    public Administrator findById(Integer id) {
        return administratorDAO.findById(id);
    }

    @Override
    public Result<Administrator> updatePwd(Administrator administratorPo) {
        Result<Administrator> result = new Result<>();
        try {
            //通过id获取客户的信息
            Administrator administratorDo = administratorDAO.findById(administratorPo.getId());
            //通过加密设置新的密码
            administratorDo.setPassword(DigestUtils.md5Hex(administratorPo.getPassword()));
            //修改原密码
            administratorDAO.updatePwd(administratorDo);
            result.setCode(200);
            result.setMsg("修改密码成功！");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("修改失败");
            throw new RuntimeException("修改失败" + e.getMessage());
        }
        return result;
    }

}
