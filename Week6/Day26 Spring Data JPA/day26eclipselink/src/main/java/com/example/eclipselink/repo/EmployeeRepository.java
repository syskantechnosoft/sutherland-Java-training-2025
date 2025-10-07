package com.example.eclipselink.repo;

import java.util.List;
import java.util.Optional;

import com.example.eclipselink.model.Employee;

public interface EmployeeRepository {
	
	List<Employee> findAll();
	
	Optional<Employee> findById(int id);
	
	Employee save (Employee employee);
	
	Employee update (int id, Employee employee);
	
	void deleteById(int id);

}
