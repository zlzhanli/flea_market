package com.flea.market.dao.base;

import com.flea.market.util.FLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author com.com.karl lee
 * @Date 2019/2/26
 */
public class DetachedCriteria {
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    private static final Log log = LogFactory.getLog(DetachedCriteria.class);
    private Map<String, Factor> map = new HashMap<>();
    private StringBuffer sql = new StringBuffer();
    private boolean paging = false;
    private Integer offset;
    private Integer length;
    private String order = ASC;

    public Map<String, Factor> getMap() {
        return map;
    }

    private String orderColumn;

    public void setPageing(boolean paging, Integer offset, Integer length) {
        this.paging = paging;
        this.offset = offset;
        this.length = length;
    }

    public void setSql(String sql) {
        this.sql = new StringBuffer(sql);
        if (map.size() > 0) {
            this.sql = new StringBuffer(sql).append(" where 1 = 1 ");
        }
    }

    public void add(String column, Factor factor) {
        map.put(column, factor);
    }

    public String createSQL() {
        for (String k : map.keySet()) {
            if (map.get(k) != null) {
                sql.append(map.get(k).createSQL(k));
            }
        }
        return sql.toString();
    }

    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        createSQL();
        if (orderColumn != null) {
            sql.append(" order by ").append(orderColumn).append(" ").append(order).append(" ");
        }

        if (paging) {
            String str = sql.append(" limit ?, ? ").toString();
            ps = conn.prepareStatement(str);
            int count = ps.getParameterMetaData().getParameterCount();
            ps.setObject(count, length);
            ps.setObject(count - 1, offset);
        } else {

            ps = conn.prepareStatement(sql.toString());

        }
        createFactor(ps);
        log.info(sql);
        return ps;
    }

    public void createFactor(PreparedStatement ps) throws SQLException {
        int index = 1;
        for (String k : map.keySet()) {
            if (map.get(k) != null) {
                index = map.get(k).factor(ps, index);
            }
        }
    }

    @Override
    public DetachedCriteria clone() {
        DetachedCriteria bean = new DetachedCriteria();
        bean.setMap(map);
        bean.setSql(sql);
        bean.setOrderColumn(orderColumn);
        bean.setOrder(order);
        bean.setLength(length);

        return bean;
    }

    /**
     * ASC or DESC
     *
     * @param order 排序方式
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * 设置排序
     *
     * @param orderColumn 排序列
     */
    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public void setMap(Map<String, Factor> map) {
        this.map = map;
    }

    public void setSql(StringBuffer sql) {
        this.sql = sql;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public interface Factor<T> {
        String createSQL(String column);

        int factor(PreparedStatement ps, int index) throws SQLException;
    }

    /**
     * 条件：column = value
     *
     * @param <T> column type
     */
    public static class FactorImp<T> implements Factor {
        private T value;

        /**
         * 基本条件实现 =
         *
         * @param value database column value
         */
        public FactorImp(T value) {
            this.value = value;
        }

        @Override
        public String createSQL(String column) {
            return " and " + column + " = ? ";
        }

        @Override
        public int factor(PreparedStatement ps, int index) throws SQLException {
            ps.setObject(index++, value);

            return index;
        }
    }

    public static class NoFactorImp<T> implements Factor {
        private T value;

        /**
         * 基本条件实现 =
         *
         * @param value database column value
         */
        public NoFactorImp(T value) {
            this.value = value;
        }

        @Override
        public String createSQL(String column) {
            return " and " + column + " != ? ";
        }

        @Override
        public int factor(PreparedStatement ps, int index) throws SQLException {
            ps.setObject(index++, value);

            return index;
        }
    }

    /**
     * 条件:模糊查询
     *
     * @param <T>
     */
    public static class Like<T> implements Factor {
        private String value;
        private boolean before;
        private boolean after;
        private boolean isOr;

        /**
         * 模糊查询
         *
         * @param value  模糊查询的值
         * @param before 之后模糊 eg ‘%{value}’
         * @param after  之前模糊 eg ‘{value}%’
         */
        public Like(String value, boolean before, boolean after) {
            this.value = value;
            this.before = before;
            this.after = after;
        }

        /**
         * 模糊查询
         *
         * @param value  模糊查询的值
         * @param before 之后模糊 eg ‘%{value}’
         * @param after  之前模糊 eg ‘{value}%’
         * @param isOr   连接字段是否为or
         */
        public Like(String value, boolean before, boolean after, boolean isOr) {
            this.value = value;
            this.before = before;
            this.after = after;
            this.isOr = isOr;
        }

        public Like(String value) {
            this.value = value;
        }

        @Override
        public String createSQL(String column) {
            if (isOr) {
                return " or " + column + " like ? ";
            }
            return " and " + column + " like ? ";
        }

        @Override
        public int factor(PreparedStatement ps, int index) throws SQLException {
            String value = this.value;
            if (before) {
                value = "%" + value;
            }
            if (after) {
                value = value + "%";
            }
            ps.setObject(index++, value);
            return index;
        }
    }

    public static class Between implements Factor {
        private Number min;
        private Number max;

        public Between(Number min, Number max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String createSQL(String column) {
            return " and " + column + " between ? and ? ";
        }

        @Override
        public int factor(PreparedStatement ps, int index) throws SQLException {
            ps.setObject(index++, min);

            ps.setObject(index++, max);

            return index;
        }
    }

    public static class Greater<T extends Number> implements Factor {
        private T value;

        public Greater(T value) {
            this.value = value;
        }

        @Override
        public String createSQL(String column) {
            return " and " + column + " > ? ";
        }

        @Override
        public int factor(PreparedStatement ps, int index) throws SQLException {
            ps.setObject(index++, value);
            return index;
        }
    }

    public static class Less<T extends Number> implements Factor {
        private T value;

        public Less(T value) {
            this.value = value;
        }

        @Override
        public String createSQL(String column) {
            return " and " + column + " < ? ";
        }

        @Override
        public int factor(PreparedStatement ps, int index) throws SQLException {
            ps.setObject(index++, value);
            return index;
        }
    }
}
