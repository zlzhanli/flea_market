
package com.flea.market.service;

import com.flea.market.dao.base.DetachedCriteria;
import com.flea.market.entity.PageBean;
import com.flea.market.entity.Result;
import com.flea.market.pojo.Goods;
import com.flea.market.util.AutoClose;
import com.flea.market.util.AutoCommit;

import java.util.List;

/**
 * @author karl lee
 * @Date 2019/3/8
 */
public interface GoodsService {


    /**
     * 搜索符合条件的记录
     *
     * @param criteria 条件对象
     * @param page     代表当前页数
     * @param rows     rows 每页显示记录数
     * @return pageBean
     */
    @AutoCommit
    @AutoClose
    PageBean<Goods> search(DetachedCriteria criteria, Integer page, Integer rows);

    /**
     * 商品查询
     *
     * @return
     */
    @AutoClose
    @AutoCommit
    List<Goods> list();


    /**
     * 根据id查找商品信息
     *
     * @param id
     * @return
     */

    @AutoClose
    @AutoCommit
    Goods findById(Integer id);

    /**
     * 此方法保存 商品记录<br>
     * 若给的用户id为空则执行插入操作，<br>
     * 若传入id不为空则对比数据库记录进行更新操作<br>
     *
     * @param goods 需要保存的数据库记录
     * @return Result {"code": ,"msg":"","target":null}<br>
     * target 插入时返回插入的持久化实体<br>
     */
    @AutoClose
    @AutoCommit
    Result<Goods> save(Goods goods);

    @AutoClose
    @AutoCommit
    Result<Goods> delete(Goods goods);

    /**
     * 商品上架
     * @param id
     * @return
     */
    @AutoClose
    @AutoCommit
    Result<Goods> up(Integer id);

    /**
     * 商品下架
     * @param id
     * @return
     */
    @AutoClose
    @AutoCommit
    Result<Goods> down(Integer id);
}

