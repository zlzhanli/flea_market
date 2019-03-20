package com.flea.market.web.servlet;


import com.flea.market.entity.FileView;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.service.ServiceFactory;
import com.flea.market.service.impl.CustomerServiceImpl;
import com.flea.market.util.FileUtil;
import com.flea.market.util.JSONUtil;
import com.flea.market.util.UUIDutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CustomerUpdatePhotoServlet", urlPatterns = "/updatePhoto.do")
@MultipartConfig
public class CustomerUpdatePhotoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置字符集
        response.setCharacterEncoding("utf-8");
        //设置路径
        String path = this.getServletContext().getRealPath("/") + "upload/";
        //获取图片
        final String HEAD = "Content-Disposition";
        Part photoPart = request.getPart("photo");
        String head = photoPart.getHeader(HEAD);
        String fileName = FileUtil.getFileName(head);
        String suffix = FileUtil.getSuffix(fileName);
        String newFileName = UUIDutil.getUUID() + suffix;

        // 构建文件视图
        FileView view = new FileView();
        view.setOldFileName(fileName);
        view.setNewFileName(newFileName);
        view.setPath(path);
        view.setSuffix(suffix);

        // 上传图片
        photoPart.write(path + newFileName);
        view.setMsg("上传成功");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(JSONUtil.toJson(view));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
