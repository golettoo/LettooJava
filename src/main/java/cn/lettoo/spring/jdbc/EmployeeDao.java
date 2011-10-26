package cn.lettoo.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import cn.lettoo.jdbc.Employee;
import cn.lettoo.jdbc.Department;
import cn.lettoo.jdbc.SqlParser;

public class EmployeeDao extends AbstractDao<Employee> implements
        IDao<Employee> {

    public int insert(Employee employee) {
        String sql = SqlParser.getInstance().getSql("Employee.insert");

        Object[] args;
        if (employee.getDepartment() != null) {
            args = new Object[] { 
                    employee.getId(),
                    employee.getName(),
                    employee.getDepartment().getId(),
                    employee.getDescription() };
        } else {
            args = new Object[] { 
                    employee.getId(),
                    employee.getName(),
                    java.sql.Types.NULL,
                    employee.getDescription() };
        }
        
        return jdbcTemplate.update(sql, args);
    }

    public int delete(Employee employee) {
        String sql = SqlParser.getInstance().getSql("Employee.delete");

        return jdbcTemplate.update(sql, new Object[] { employee.getId() });
    }

    public int update(Employee employee) {
        String sql = SqlParser.getInstance().getSql("Employee.update");
        Object[] args;
        if (employee.getDepartment() != null) {
            args = new Object[] { 
                    employee.getName(),
                    employee.getDepartment().getId(),
                    employee.getDescription(), 
                    employee.getId() };
        } else {
            args = new Object[] { 
                    employee.getName(), 
                    java.sql.Types.NULL,
                    employee.getDescription(), 
                    employee.getId() };
        }
        
        return jdbcTemplate.update(sql, args);
    }

    public Employee select(Object id) {
        String sql = SqlParser.getInstance().getSql("Employee.select.id");
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
    }

    public List<Employee> selectList(String condition) {
        String sql = SqlParser.getInstance().getSql("Employee.select.condition") + condition;
        return jdbcTemplate.query(sql, new EmployeeRowMapper());        
    }
    
    private static final class EmployeeRowMapper implements RowMapper {

        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            
            employee.setId(rs.getInt("ID"));
            employee.setName(rs.getString("NAME"));
            employee.setDescription(rs.getString("DESCRIPTION"));
            
            if (rs.getString("DEPARTMENTID") != null) {
                Department department = new Department();
                department.setId(rs.getInt("DEPARTMENTID"));
                employee.setDepartment(department);
            }
            
            return employee;
        }
        
    }

}
