package com.flea.market.web.action;

import com.flea.market.entity.Result;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.Order;
import com.flea.market.service.OrderService;
import com.flea.market.service.ReceiverAddressService;
import com.flea.market.service.ShippingMethodService;
import com.flea.market.util.Fordword;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/11
 */
public class OrderAction {

    @Resource(name = "orderService")
    private OrderService orderService;


    @Fordword
    public String createOrder(HttpServletRequest request, HttpServletResponse response) {
        //获取复选框
        String[] strCartIds = request.getParameterValues("cartIds");
        String strType = request.getParameter("shipping");
        String strAdd = request.getParameter("address");
        //获取用户信息
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        if (strType == null || strAdd == null || strCartIds.length == 0) {
            List<Order> list = orderService.listOrder(customer.getId());
            request.setAttribute("listOrder",list );
            return "jsp/customer/order.jsp";
        }

        Integer type = Integer.parseInt(strType);
        Integer address = Integer.parseInt(strAdd);

        Integer[] cartIds = new Integer[strCartIds.length];
        for (int i = 0; i < strCartIds.length; i++) {
            cartIds[i] = Integer.parseInt(strCartIds[i]);
        }
        orderService.createOrder(cartIds, customer, type, address);
        List<Order> list = orderService.listOrder(customer.getId());
        request.setAttribute("listOrder",list );

        return "jsp/customer/order.jsp";
    }


    @Fordword
    public String list(HttpServletRequest request, HttpServletResponse response) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        List<Order> ls = orderService.listOrder(customer.getId());
        request.setAttribute("listOrder", ls);
        return "jsp/customer/myorder.jsp";
    }


}
