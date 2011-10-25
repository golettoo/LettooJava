package cn.lettoo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {

    /**
     * @param args
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    	Connection conn = DBUtil.getConnection();
    	EmployeeDB empDB = new EmployeeDB();
    	DepartmentDB deptDB = new DepartmentDB();
    	// Clean data first
    	conn = DBUtil.getConnection();
    	empDB.cleanEmployee(conn);    	
    	conn = DBUtil.getConnection();
    	deptDB.cleanDepartment(conn);
    	
        Department dept1 = new Department();
        dept1.setId(1);
        dept1.setName("研发部");
        dept1.setDescription("研发部很不错！");
        conn = DBUtil.getConnection();
        deptDB.addDepartment(conn, dept1);
        
        Department dept2 = new Department();
        dept2.setId(2);
        dept2.setName("商务部");
        dept2.setDescription("商务部也很不错！");
        conn = DBUtil.getConnection();
        deptDB.addDepartment(conn, dept2);
        
        List<Employee> empList = new ArrayList<Employee>();
    	for (int i = 1; i <= 10; i ++) {
    		Employee emp = new Employee();
    		emp.setId(i);
    		emp.setName("name" + i);
    		emp.setDepartment(dept1);
    		emp.setDescription("auto generate employee " + i);
    		
    		empList.add(emp);
    	}
    	conn = DBUtil.getConnection();
    	empDB.addEmployees(conn, empList, 5);
    	
    	conn = DBUtil.getConnection();
    	deptDB.deleteDepartmentTrans(conn, dept1);
    	
    	//empDB.test();
    }

}
