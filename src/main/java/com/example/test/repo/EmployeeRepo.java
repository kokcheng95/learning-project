package com.example.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	Employee findByName(String name);
}
