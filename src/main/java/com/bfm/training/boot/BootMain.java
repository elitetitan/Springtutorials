package com.bfm.training.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bfm.training.jdbc.Employee;
import com.bfm.training.jdbc.SpringJDBC;
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.bfm.training")
@PropertySource(value = { "classpath:application.properties" })

public class BootMain {

//    @RequestMapping("/app")
//    String home() {
//        return "Hello World!";
//    }
//
//    @RequestMapping("/app2")
//    void home2() throws Exception {
//      new SampleJDBC().main(null);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(new Class[]{BootMain.class}, args);
//    }
//    
    @Autowired
    private SpringJDBC springJDBC;

    @RequestMapping("/app")
    String home() {
        return "Hello World!";
    }

    @GetMapping("/employee/get-all")
    List<Employee> findAll() throws Exception {
        return springJDBC.displayEmployeeList();
    }

    @GetMapping("/employee/{id}/{Path}")
    Employee findEmp(@PathVariable("id") Integer id, @RequestParam String name) throws Exception {
        return springJDBC.findEmployee(id);
    }
    
    @GetMapping("/employee")
    List<Employee> findAllName(@RequestParam String name) throws Exception {
    	return springJDBC.findAllEmployees(name);
    }

    @PostMapping("/employee/add")
    int addEmp(@RequestBody Employee employee) throws Exception {
        return springJDBC.insertEmployee(employee);
    }

    @DeleteMapping("/employee/{id}")
    int deleteEmp(@PathVariable("id") Integer id) throws Exception {
        return springJDBC.deleteEmployee(id);
    }
    
    
    public static void main(String[] args) {
        SpringApplication.run(new Class[]{BootMain.class}, args);
    }


}
