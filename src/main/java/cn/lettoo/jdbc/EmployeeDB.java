package cn.lettoo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDB {

	public void addEmployees(Connection conn, List<Employee> empList, int batchSize)
	        throws SQLException {
		long bt = System.currentTimeMillis();
		PreparedStatement stmt = null;
		try {
			String sql = SqlParser.getInstance().getSql("Employee.insert");
			stmt = conn.prepareStatement(sql);
			int count = 0;
			for (Employee emp : empList) {
				stmt.setInt(1, emp.getId());
				stmt.setString(2, emp.getName());
				stmt.setInt(3, emp.getDepartment().getId());
				stmt.setString(4, emp.getDescription());
				stmt.addBatch();
				
				count++;				
				if (count % batchSize == 0) {
					stmt.executeBatch();
				}
			}
			
			stmt.executeBatch();

		} finally {
			long et = System.currentTimeMillis();
			System.out.println(String.format("用时%dms", et-bt));
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void cleanEmployee(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		try {
			// SqlParser读取sql.xml文件
			String sql = SqlParser.getInstance().getSql("Employee.clean");
			stmt = conn.prepareStatement(sql);			
			stmt.execute();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
    }
}
