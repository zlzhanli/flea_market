package com.flea.market.dao.base;

import com.flea.market.exception.ColnumPrimeException;
import com.flea.market.exception.MySQLException;
import com.flea.market.exception.NewInstanceException;
import com.flea.market.util.Column;
import com.flea.market.util.DbUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author com.com.karl lee
 * @Date 2019/2/26
 */
public abstract class SupBaseDAO<T> implements BaseDAO<T> {
    private static final Log log = LogFactory.getLog(SupBaseDAO.class);

    /**
     * 根据结果集构建一个实体类对象
     *
     * @param rs    数据库结构集
     * @param clazz 需要构建的数据实体类类文件对象
     * @return 构建好的实体类对象
     * @throws SQLException 数据构建异常，来自数据库的异常
     */
    protected T create(ResultSet rs, Class<T> clazz) throws SQLException {

        T target = null;
        try {
            target = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            log.error("通过反射创建对象失败,检查{" + clazz + "}是否有空参构造方法", e);
            throw new NewInstanceException("通过反射创建对象失败，请检查你传入的class是否正确\n" +
                    "Failed to create an object by reflection. Check that your incoming" +
                    " class is correct (" + clazz + ")");
        }
        target.getClass().getDeclaredFields();
        Method[] methods = target.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().startsWith("set")) {
                Column annotation = methods[i].getAnnotation(Column.class);
                if (annotation == null) {
                    continue;
                }
                String colnum = annotation.name();
                if ("".equals(colnum)) {
                    throw new RuntimeException("" + target.getClass().getPackage() + target.getClass().getName());
                }
                try {
                    methods[i].invoke(target, rs.getObject(colnum));
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("构建持久化对象时出现异常，在调用set方法时：列【" + colnum + "】" + Arrays.toString(methods[i].getParameterTypes()), e);
                    throw new ColnumPrimeException("\n" +
                            "属性填装时发生异常，可能是你配置的数据库表中的字段名不存在，或者是类型不匹配\n" +
                            "An exception occurs when the property is loaded. It may be that the field" +
                            " name in the database table you configured does not exist, or that the type " +
                            "does not match.(" + colnum + ")");
                }
            }
        }
        return target;
    }

    /**
     * 查询所有
     *
     * @param sql   执行的SQL语句，例如 select * from tableName
     * @param clazz 查询的实体类类文件
     * @return 查寻到的数据实体类对象列表
     */
    protected List<T> list(String sql, Class<T> clazz) {

        List<T> list = new ArrayList<>();
        try (PreparedStatement ps = DbUtil.getConn().prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {

                log.info("method list execute:" + ps);
                while (rs.next()) {
                    list.add(create(rs, clazz));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }
        return list;
    }

    /**
     * 分页条件查询
     * 使用时仅仅书写简单的查询
     * 传入封装好的条件对象即可
     *
     * @param sql              例如 select * from tableName
     * @param clazz            查询的实体类类文件
     * @param detachedCriteria 条件对象
     * @return 查寻到的数据实体类对象列表
     */
    protected List<T> listByPage(String sql, Class<T> clazz, DetachedCriteria detachedCriteria) {

        List<T> list = new ArrayList<>();
        detachedCriteria.setSql(sql);
        try (PreparedStatement ps = detachedCriteria.createPreparedStatement(DbUtil.getConn())) {
            try (ResultSet rs = ps.executeQuery()) {
                log.info("method listByPage execute:" + ps);
                while (rs.next()) {
                    list.add(create(rs, clazz));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }
        return list;
    }

    protected List<T> listByPage2(String sql, Class<T> clazz, DetachedCriteria detachedCriteria) {

        List<T> list = new ArrayList<>();
        detachedCriteria.setSql(sql);
        try (PreparedStatement ps = detachedCriteria.createPreparedStatement(DbUtil.getConn())) {
            try (ResultSet rs = ps.executeQuery()) {
                log.info("method listByPage2 execute:" + ps);
                while (rs.next()) {
                    list.add(create(rs, clazz));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }
        return list;
    }

    protected void create(String sql, DetachedCriteria criteria) {
        criteria.setSql(sql);
        try (PreparedStatement ps = criteria.createPreparedStatement(DbUtil.getConn())) {
            ps.executeUpdate();
            log.info("method create execute:" + ps);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }
    }

    /**
     * 查询
     * 支持对单个表的查询，查询符合条件的多条记录
     *
     * @param sql   查询的SQL语句
     * @param clazz 查询的实体类对象类
     * @param args  参数列表为可变长参数，参数顺序对应SQL语句中？顺序
     * @return 查寻到的数据实体类对象列表
     */
    protected List<T> list(String sql, Class<T> clazz, Object... args) {

        List<T> list = new ArrayList<>();
        try (PreparedStatement ps = DbUtil.getConn().prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                log.info("method list execute:" + ps);
                while (rs.next()) {
                    list.add(create(rs, clazz));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }
        return list;
    }

    /**
     * 查询
     * 支持对单个表的查询操作
     *
     * @param sql   查询时执行的SQL语句
     * @param clazz 查询的实体类
     * @param args  参数列表为可变长参数，参数顺序对应SQL语句中？顺序
     * @return 查询到的数据实体类
     */
    protected T find(String sql, Class<T> clazz, Object... args) {

        try (PreparedStatement ps = DbUtil.getConn().prepareStatement(sql)) {

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                log.info("method find execute:" + ps);
                if (rs.next()) {
                    return create(rs, clazz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }
        return null;
    }

    /**
     * 此方法可进行增删改操作
     *
     * @param sql  需要执行的SQL语句，条件参数需用？表示
     * @param args 参数列表为可变长参数，参数顺序对应SQL语句中？顺序
     * @return 插入语句或者更新语句记录的主键id
     */
    protected Integer execute(String sql, Object... args) {

        try (PreparedStatement ps = DbUtil.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();
            log.info("method execute execute:" + ps);
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }
        return -1;
    }

    /**
     * 执行SQL语句，返回受影响的行数
     *
     * @param sql  SLQ 语句
     * @param args SQL语句中？处的参数，需按其顺序传入
     * @return 受影响的行数
     */
    protected Integer execute2(String sql, Object... args) {

        try (PreparedStatement ps = DbUtil.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            log.info("method execute2 execute:" + ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }

    }

    /**
     * 统计符合条件的记录数
     *
     * @param sql              eg ：select count(*) as count from tableName
     * @param detachedCriteria 查询条件
     * @return 符合条件的记录数
     */
    protected Integer count(String sql, DetachedCriteria detachedCriteria) {
        detachedCriteria = detachedCriteria.clone();
        detachedCriteria.setSql(sql);
        try (PreparedStatement ps = detachedCriteria.createPreparedStatement(DbUtil.getConn())) {
            try (ResultSet rs = ps.executeQuery()) {
                log.info("method count execute:" + ps);
                if (rs.next()) {

                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            log.info("method list execute:");
            e.printStackTrace();
            throw new MySQLException("SQLException:" + e.getMessage());
        }
        return 0;
    }
}
