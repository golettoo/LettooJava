package cn.lettoo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDB {

	// 增
	public void addDepartment(Connection conn, Department dept)
	        throws SQLException {
		PreparedStatement stmt = null;
		try {
			// SqlParser读取sql.xml文件
			String sql = SqlParser.getInstance().getSql("Department.insert");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dept.getId());
			stmt.setString(2, dept.getName());
			stmt.setString(3, dept.getDescription());
			stmt.execute();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void deleteDepartmentTrans(Connection conn, Department dept)
	        throws SQLException {
		try {			
			conn.setAutoCommit(false);
			this.deleteDepartment(conn, dept);
			this.updateEmpDeptNull(conn, dept);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
		}

	}

	private void deleteDepartment(Connection conn, Department dept)
	        throws SQLException {
		PreparedStatement stmt = null;		
		try {
			String sql = SqlParser.getInstance().getSql("Department.delete");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dept.getId());
			stmt.execute();

		} finally {
			DBUtil.close(stmt);
			//DBUtil.close(conn);
		}
	}

	private void updateEmpDeptNull(Connection conn, Department dept)
	        throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = SqlParser.getInstance().getSql(
			        "Employee.departmentisnull");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dept.getId());
			stmt.execute();

		} finally {
			DBUtil.close(stmt);
			//DBUtil.close(conn);
		}
	}

	// 改
	public void updateDepartment(Connection conn, Department dept)
	        throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = SqlParser.getInstance().getSql("Department.update");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dept.getName());
			stmt.setString(2, dept.getDescription());
			stmt.setInt(3, dept.getId());
			stmt.execute();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	// 查
	public List<Department> selectDepartment(Connection conn, int id)
	        throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = SqlParser.getInstance().getSql("Department.select");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			List<Department> deptList = new ArrayList<Department>();

			while (rs.next()) {
				Department dept = new Department();
				dept.setId(rs.getInt("ID"));
				dept.setName(rs.getString("NAME"));
				dept.setDescription(rs.getString("DESCRIPTION"));
				deptList.add(dept);
			}

			return deptList;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void cleanDepartment(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		try {
			// SqlParser读取sql.xml文件
			String sql = SqlParser.getInstance().getSql("Department.clean");
			stmt = conn.prepareStatement(sql);
			stmt.execute();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
}
