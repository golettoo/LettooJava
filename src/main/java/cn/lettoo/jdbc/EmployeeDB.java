package cn.lettoo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDB {

	public void addEmployees(Connection conn, List<Employee> empList,
	        int batchSize) throws SQLException {
		long bt = System.currentTimeMillis();

		PreparedStatement stmt = null;
		// 设置conn自动提交为false，而由程序在需要的地方进行commit或者rollback
		conn.setAutoCommit(false);
		//Savepoint sp = conn.setSavepoint();
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
					// 注意，如果不加conn.commit()方法，即使我用stmt.executeBatch()，也不会真正提交到数据库
					conn.commit();
				}
			}
			stmt.executeBatch();
			// 虽然我在上面多次调用executeBatch()，但都没有真正提交到数据库，只有在conn.commit()之后才算真正提交
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(String.format("用时%dms", et - bt));
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void updateEmployee(Connection conn, Employee emp)
	        throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = SqlParser.getInstance().getSql("Employee.update");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emp.getName());
			if (emp.getDepartment() == null) {
				stmt.setNull(2, java.sql.Types.NULL);
			} else {
				stmt.setInt(2, emp.getDepartment().getId());
			}
			stmt.setString(3, emp.getDescription());
			stmt.execute();
		} finally {
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
	
	public void test() {
		ApplicationContext context = 
	            new ClassPathXmlApplicationContext(new String[] { "server.xml" });
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		jdbcTemplate.setDataSource(dataSource);
		
		String sql = SqlParser.getInstance().getSql("Employee.test");
		List<Employee> empList = jdbcTemplate.query(sql, new RowMapper<Employee>(){

			public Employee mapRow(ResultSet rs, int rowNum) {
                Employee emp = new Employee();
                try {
	                emp.setId(rs.getInt("id"));
	                emp.setName(rs.getString("name"));
	                emp.setDepartment(new Department());
	                emp.getDepartment().setId(rs.getInt("department.id"));
	                emp.getDepartment().setName(rs.getString("department.name"));	                
                } catch (SQLException e) {
	                emp = null;
                }
                
                return emp;			
            }
			
		},2);
		System.out.println(empList);
	}

}
