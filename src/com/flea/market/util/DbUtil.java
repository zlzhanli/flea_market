package com.flea.market.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {
    static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private static DataSource dataSource = new ComboPooledDataSource();

    public synchronized static Connection getConn() throws SQLException {

        if (connectionThreadLocal.get() == null || connectionThreadLocal.get().isClosed()) {
            connectionThreadLocal.set(dataSource.getConnection());
            return connectionThreadLocal.get();
        }
        return connectionThreadLocal.get();
    }

    public synchronized static void close() throws SQLException {
        if (connectionThreadLocal.get() != null) {
            connectionThreadLocal.get().close();
            connectionThreadLocal.remove();
        }
    }

}
