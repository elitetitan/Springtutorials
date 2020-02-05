package com.bfm.training.jdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class SpringJDBC {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int totalCount() {
        int count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM EMP", Integer.class);
        return count;
    }

    public List<Object[]> displayEmployeeRecords() {
        return jdbcTemplate.query("SELECT * FROM EMP", new RowMapper<Object[]>() {
            public Object[] mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Object[]{resultSet.getInt("empno"), resultSet.getString("ename"), resultSet.getInt("mgr")};
            }
        });
    }
    
    public List<Employee> findAllEmployees(String name) {
    	return jdbcTemplate.query("SELECT * FROM EMP WHERE ENAME = ?",new Object[] {name}, new RowMapper<Employee>() {
    		public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Employee(resultSet.getInt("empno"), resultSet.getString("ename"), resultSet.getInt("mgr"), resultSet.getInt("sal"), resultSet.getInt("comm"));
    	}
    });
    }

    public List<Map<String, Object>> displayEmployeeRecords2() {
        return jdbcTemplate.queryForList("SELECT * FROM EMP");
    }

    public List<Employee> displayEmployeeList() {
        return jdbcTemplate.query("SELECT * FROM EMP", new RowMapper<Employee>() {
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Employee(resultSet.getInt("empno"), resultSet.getString("ename"), resultSet.getInt("mgr"), resultSet.getInt("sal"), resultSet.getInt("comm"));
            }
        });
    }

    public Employee findEmployee(int empNo) {
        return jdbcTemplate.queryForObject("SELECT * FROM EMP where empno=?", new Object[]{empNo}, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    public int insertEmployee(Employee employee) {
        String insertQuery = "insert into EMP (empno, ename, mgr, sal, comm) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(insertQuery, employee.getEmpno(), employee.getEname(), employee.getMgrid(), employee.getSal(), employee.getComm());
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        SpringJDBC springJDBC = applicationContext.getBean(SpringJDBC.class);
        System.out.println(springJDBC.totalCount());
        System.out.println(springJDBC.displayEmployeeRecords());
        System.out.println(springJDBC.displayEmployeeList());
        springJDBC.insertEmployee(new Employee(11, "Rama", 10, 5000, 1200));
        System.out.println("------------------------------------");
        System.out.println(springJDBC.displayEmployeeList());
        System.out.println("------------------------------------");
        System.out.println(springJDBC.displayEmployeeRecords2());

    }

    public int deleteEmployee(Integer id) {
        String deleteQuery = "delete from EMP where empno = ?";
        return jdbcTemplate.update(deleteQuery,id);
    }
}