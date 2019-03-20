package com.flea.market.web.action;


import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Cart;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.Goods;
import com.flea.market.service.CartService;
import com.flea.market.service.GoodsService;
import com.flea.market.util.Fordword;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zl
 * @time 2019/3/8
 */
public class CartAction {

    @Resource(name = "cartService")
    private CartService cartService;
    @Resource(name = "goodsService")
    private GoodsService goodsService;

    private String goodsName;
    private String goodsCover;
    private BigDecimal goodsPrice;
    private Integer num;
    private Integer goodsId;
    private Integer customerId;
    private Customer customer;
    private Goods goods;

    private Integer cartId;
    private Integer tag;


    public String addCart(HttpServletRequest request, HttpServletResponse response) {
        //添加购物车并返回购物车商品数量

        //添加购物车
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        Result result = cartService.add(goodsId, customer);

        //获取商品数量
        DetachedCriteria criteria = new DetachedCriteria();
        criteria.add("customer",new DetachedCriteria.FactorImp<>(customer.getId()));
        Integer count = cartService.countByCart(criteria);

        Map map= new HashMap();
        map.put("code",result.getCode());
        map.put("msg",result.getMsg());
        map.put("count",count);


        return JSONObject.fromObject(map).toString();
    }

    public String updateCart(HttpServletRequest request, HttpServletResponse response) {


        Result result = cartService.updateNum(cartId, tag, num);
        return JSONObject.fromObject(result).toString();
    }

    public String deleteCart(HttpServletRequest request, HttpServletResponse response) {
        Cart cart = cartService.findById(cartId);
        Result result = cartService.delete(cart);
        return JSONObject.fromObject(result).toString();
    }

    public String countCart(HttpServletRequest request, HttpServletResponse response){
        DetachedCriteria criteria = new DetachedCriteria();
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer == null) {
            return "0";
        }
        criteria.add("customer",new DetachedCriteria.FactorImp<>(customer.getId()));

        Integer count = cartService.countByCart(criteria);

        return  count.toString();
    }

    @Fordword
    public String lsCarts(HttpServletRequest request, HttpServletResponse response) {

        Customer customer= (Customer) request.getSession().getAttribute("customer");
        if (customer == null) {
            return "html/index.html";
        }
        List<Cart> ls = cartService.lsCarts(customer.getId());

        request.setAttribute("lsCarts", ls);
        request.setAttribute("size", ls.size());
        return "jsp/customer/cart.jsp";
    }

    @Fordword
    public String goListCarts(HttpServletRequest request, HttpServletResponse response) {

        Customer customer= (Customer) request.getSession().getAttribute("customer");
        List<Cart> ls = cartService.lsCarts(customer.getId());


        request.setAttribute("lsCarts", ls);
        return "jsp/customer/mycart.jsp";
    }

    /**
     * 创建cart
     *
     * @param cart
     */
    private void createCart(Cart cart) {
        cart.setGoodsName(goodsName);
        cart.setGoodsCover(goodsCover);
        cart.setGoodsPrice(goodsPrice);
        cart.setNum(num);
        cart.setCustomer(customer);
        cart.setGoods(goods);
    }

}
