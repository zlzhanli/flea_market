package com.flea.market.service.impl;


import com.flea.market.dao.CartDAO;
import com.flea.market.dao.GoodsDAO;
import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Cart;
import com.flea.market.pojo.Customer;
import com.flea.market.pojo.Goods;
import com.flea.market.service.CartService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zl
 * @time 2019/3/8
 */
public class CartServiceImpl implements CartService {

    @Resource(name = "cartDAO")
    private CartDAO cartDAO;
    @Resource(name = "goodsDAO")
    private GoodsDAO goodsDAO;
    @Override
    public Result<Cart> updateNum(Integer cartId, Integer tag, Integer num) {
        Result<Cart> result = new Result<>();
        try {
            Cart cart = cartDAO.findById(cartId);
            if (tag == 0) {
                cart.setNum(cart.getNum() + 1);
                cartDAO.update(cart);
                result.setCode(200);
                return result;
            }
            if (tag == 3) {
                cart.setNum(num);
                cartDAO.update(cart);
                result.setCode(200);
                return result;
            }else{
                if (cart.getNum() == 1) {
                    cartDAO.delete(cart);
                    result.setCode(200);
                    return result;
                } else {
                    cart.setNum(cart.getNum() - 1);
                    cartDAO.update(cart);
                    result.setCode(200);
                    return result;
                }
            }




        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("修改失败");
            e.printStackTrace();
            throw new RuntimeException("修改失败" + e.getMessage());
        }
    }

    @Override
    public List<Cart> lsCarts(Integer customerId) {
        System.out.println(cartDAO.list(customerId));
        return cartDAO.list(customerId);
    }

    @Override
    public Result<Cart> add(Integer goodsId, Customer customer) {
        Result<Cart> result = new Result<>();
        try {
            Cart cart = cartDAO.getCartByCstomerAndGoods(customer.getId(), goodsId);
            if (cart == null) {
                Goods goods = goodsDAO.findById(goodsId);
                cart = new Cart(null, goods.getGoodsName(), goods.getGoodsCover(), goods.getGoodsPrice(), 1, customer, goods);
                cartDAO.save(cart);
                result.setCode(200);
                result.setMsg("添加成功");
            } else {
                cart.setNum(cart.getNum() + 1);
                cartDAO.update(cart);
                result.setCode(200);
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("添加失败");
            e.printStackTrace();
            throw new RuntimeException("添加失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Cart> delete(Cart cart) {
        Result<Cart> result = new Result<>();
        try {
            cartDAO.delete(cart);
            result.setCode(200);
            result.setMsg("删除成功");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("删除失败");
            e.printStackTrace();
            throw new RuntimeException("删除失败" + e.getMessage());
        }
        return result;
    }

    @Override
    public Cart findById(Integer cartId) {
        return cartDAO.findById(cartId);
    }

    @Override
    public Integer countByCart(DetachedCriteria criteria) {
        Integer count = cartDAO.count(criteria);
        return count;
    }
}
