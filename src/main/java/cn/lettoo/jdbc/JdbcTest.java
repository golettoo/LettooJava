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
    	
    	
    	doTest(5);
    	doTest(10);
    	doTest(50);
    	doTest(100);
    	doTest(500);
    	doTest(1000);
    	
        /*DepartmentDB deptDB = new DepartmentDB();
        Department dept1 = new Department();
        dept1.setId(1);
        dept1.setName("研发部");
        dept1.setDescription("研发部很不错！");
        deptDB.addDepartment(conn, dept1);
        
        conn = DBUtil.getConnection();
        Department dept2 = new Department();
        dept2.setId(2);
        dept2.setName("商务部");
        dept2.setDescription("商务部也很不错！");
        deptDB.addDepartment(conn, dept2);
        
        conn = DBUtil.getConnection();
        deptDB.deleteDepartment(conn, dept1);
        
        conn = DBUtil.getConnection();
        dept2.setName("新商务部");
        dept2.setDescription("新商务更好！");
        deptDB.updateDepartment(conn, dept2);
        
        conn = DBUtil.getConnection();
        List<Department> deptList = deptDB.selectDepartment(conn, 2);
        for (Department dept : deptList) {
        	System.out.println(dept.getId() + dept.getName() + dept.getDescription());
        }*/
        
    }
    
    private static void doTest(int batchSize) throws SQLException {
    	Connection conn = DBUtil.getConnection();
    	EmployeeDB empDB = new EmployeeDB();
    	DepartmentDB deptDB = new DepartmentDB();
    	// Clean data first
    	empDB.cleanEmployee(conn);    	
    	deptDB.cleanDepartment(conn);
    	
    	Department dept1 = new Department();
        dept1.setId(1);
        dept1.setName("研发部");
        dept1.setDescription("研发部很不错！");
        deptDB.addDepartment(conn, dept1);
    	
    	List<Employee> empList = new ArrayList<Employee>();
    	for (int i = 1; i <= 1000; i ++) {
    		Employee emp = new Employee();
    		emp.setId(i);
    		emp.setName("name" + i);
    		Department dept = new Department();
    		emp.setDepartment(dept);
    		dept.setId(1);
    		emp.setDescription("auto generate employee " + i);
    		
    		empList.add(emp);
    	}
    	empDB.addEmployees(conn, empList, batchSize);
    }

	public static void close(Statement stmt) {
    	if (stmt != null) {
    		try {
				stmt.close();
			} catch (SQLException e) {
				// Do nothing
			}
    	}
    }

}
