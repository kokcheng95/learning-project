package com.example.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.entity.Employee;
import com.example.test.exception.handler.EmployeeNotFoundException;
import com.example.test.repo.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee saveEmp(Employee employee) {
		return employeeRepo.save(employee);
	}

	public List<Employee> saveEmps(List<Employee> employees) {
		return employeeRepo.saveAll(employees);
	}

	public List<Employee> getAllEmps() {
		return employeeRepo.findAll();
	}

	public Employee getEmpById(Long id, String path) {
		return employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id, path));
	}

	public Employee getEmpByName(String name) {
		return employeeRepo.findByName(name);
	}

	public String deleteEmp(Long id) {
		employeeRepo.deleteById(id);
		return "Employee deleted id =>" + id;
	}

	public Employee updateEmp(Employee employee) {
		Employee existingEmployee = employeeRepo.findById(employee.getId()).orElse(null);
		employee.setId(existingEmployee.getId());
		return employeeRepo.save(employee);
	}
}
