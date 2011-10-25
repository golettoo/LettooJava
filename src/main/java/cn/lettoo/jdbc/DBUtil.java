package cn.lettoo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {

    private static DataSource dataSource;

    public static Connection getConnection() {
        if (dataSource == null) {
            buildDataSource();
        }

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            return null;
        }
    }

    private static synchronized void buildDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:test.db");
        ds.setMaxActive(10);
        ds.setMaxIdle(3);
        ds.setDefaultAutoCommit(false);
        //ds.setTestOnBorrow(true);
        //ds.setValidationQuery("SELECT 1 FROM employee");

        dataSource = ds;
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            }            
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {

            }
            stmt = null;
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

            }
            rs = null;
        }
    }
}
