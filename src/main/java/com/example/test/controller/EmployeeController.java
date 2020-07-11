package com.example.test.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.entity.Employee;
import com.example.test.exception.handler.EmployeeNotFoundException;
import com.example.test.repo.EmployeeRepo;
import com.example.test.service.EmployeeService;

@RestController
@RequestMapping("/test/")
public class EmployeeController {
	Logger logger = LogManager.getLogger(EmployeeController.class);
	private static final String PARENT_PATH = "/test/";
	private static final String ALL_EMPLOYEE = "employees";
	private static final String SAVE_EMPLOYEE = "insert/employee";
	private static final String FIND_EMPLOYEE = "find/employeeById/{id}";
	private static final String FIND_EMPLOYEE_BY_NAME = "find/employeeByName/{name}";

	@Autowired
	private EmployeeService employeeService;

	// Aggregate root
	@GetMapping(ALL_EMPLOYEE)
	public List<Employee> all() {
		logger.debug("List All");
		return employeeService.getAllEmps();
	}

	@PostMapping(SAVE_EMPLOYEE)
	public Employee newEmployee(@RequestBody Employee newEmployee) {
		logger.debug("Save newEmployee {}", newEmployee);
		return employeeService.saveEmp(newEmployee);
	}

	// Single item

	@GetMapping(FIND_EMPLOYEE)
	public Employee findById(@PathVariable Long id) {
		logger.debug("find FIND_EMPLOYEE id => {}", id);
		return employeeService.getEmpById(id, PARENT_PATH + FIND_EMPLOYEE);
	}

	@GetMapping(FIND_EMPLOYEE_BY_NAME)
	public Employee findByName(@PathVariable String name) {
		logger.debug("find FIND_EMPLOYEE_BY_NAME id => {}", name);
		Employee result =employeeService.getEmpByName(name);
		if(result !=null) {
			return result;
		}
		throw new EmployeeNotFoundException("name",name, PARENT_PATH+FIND_EMPLOYEE_BY_NAME);
	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		logger.debug("updateEmployee => {}", id);
		return employeeService.updateEmp(newEmployee);
	}

//	@PutMapping("/employees/{name}")
//	public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//		return employeeService.findById(id).map(employee -> {
//			employee.setName(newEmployee.getName());
//			employee.setRole(newEmployee.getRole());
//			return employeeService.save(employee);
//		}).orElseGet(() -> {
//			newEmployee.setId(id);
//			return employeeService.save(newEmployee);
//		});
//	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		logger.debug("deleteEmployee => {}", id);
		employeeService.deleteEmp(id);
	}
}