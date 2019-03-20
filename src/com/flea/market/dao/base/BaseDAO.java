package com.flea.market.dao.base;

import java.util.List;

/**
 * @author com.com.karl lee
 * @Date 2019/2/26
 */
public interface BaseDAO<T> {

    /**
     * 查询所有的持久化类
     *
     * @return list
     */
    List<T> list();

    /**
     * 根据条件查询所有的持久化类
     *
     * @param detachedCriteria 条件
     * @param begin            起始位置
     * @param pageSize         查询数量
     * @return list
     */
    List<T> listByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    /**
     * @param id id
     * @return T
     */
    T findById(Integer id);

    /**
     * 保存
     *
     * @param obj target
     */
    void save(T obj);

    /**
     * 删除
     *
     * @param obj target
     */
    void delete(T obj);

    /**
     * 虚拟删除
     * @param obj 删除的实体，务必在传入时检查传入对象是否有主键存在
     */
    void remove(T obj);

    /**
     * 更新
     *
     * @param obj target 务必在传入时检查传入对象是否有主键存在
     */
    void update(T obj);

    /**
     * 统计符合条件的持久化类个数
     *
     * @param detachedCriteria 条件
     * @return int
     */
    Integer count(DetachedCriteria detachedCriteria);
}
