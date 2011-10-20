package cn.lettoo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			String url = "jdbc:sqlite:test.db";
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(url);
			} catch (ClassNotFoundException e) {
				conn = null;
			} catch (SQLException e) {
				conn = null;
			}

		}
		return conn;
	}

	public static void close(Connection conn) {
		// ��Ϊconnectionֻ��һ�������Բ������ر�
		/*
		 * if (conn != null) { try { conn.close(); } catch (SQLException e) {
		 * 
		 * } conn = null; }
		 */
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {

			}
			conn = null;
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
