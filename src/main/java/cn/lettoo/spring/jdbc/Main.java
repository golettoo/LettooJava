package cn.lettoo.spring.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lettoo.jdbc.Department;
import cn.lettoo.jdbc.Employee;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = 
                new ClassPathXmlApplicationContext(new String[] { "server.xml" });
        
        Employee emp = new Employee();
        emp.setId(999);
        emp.setName("testtest");
        emp.setDepartment(new Department());
        emp.getDepartment().setId(2);
        emp.setDescription("testtest");
      
        EmployeeDao empDao = (EmployeeDao) context.getBean("employeeDao");
        empDao.insert(emp);
        
        Employee emp1 = empDao.select(999);        
        emp1.setName("newname");
       
        empDao.update(emp1);
        
        List<Employee> empList = empDao.selectList("");
        System.out.println(empList.size());
        
        empDao.delete(emp1);
        
        empList = empDao.selectList("");
        System.out.println(empList.size());
    }

}
