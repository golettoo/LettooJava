package cn.lettoo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.lettoo.mybatis.domain.Department;
import cn.lettoo.mybatis.domain.Employee;

public class JdbcTest {

    /**
     * @param args
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:sqlite:test.db";
        Class.forName("org.sqlite.JDBC");
        Connection conn=DriverManager.getConnection(url);
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID, NAME, DEPARTMENTID, DESCRIPTION FROM EMPLOYEE");
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            int departmentId = rs.getInt("DEPARTMENTID");
            String description = rs.getString("DESCRIPTION");
            
            Employee emp = new Employee();
            emp.setId(id);
            emp.setName(name);
            Department dep = new Department();
            dep.setId(departmentId);
            emp.setDepartment(dep);
            emp.setDescription(description);
            
            System.out.println(emp.toString());
        }
        
        rs.close();
        
        stmt.close();
        
        conn.close();
        
    }

}
