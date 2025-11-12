package com.example.sparkprocedure.controller;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparkprocedure.service.SparkSQLService;

@RestController
@RequestMapping("/api/spark/employees")
public class EmployeeController {
	
	private final SparkSQLService sparkSQLService;

	public EmployeeController(SparkSQLService sparkSQLService) {
		super();
		this.sparkSQLService = sparkSQLService;
		
		sparkSQLService.registerCustomFunctions();
		sparkSQLService.createEmployeesStoredProcedure();
		sparkSQLService.createTempView();
	}
	
	@GetMapping("/department/{dept}")
	public List<String> getByDepartment(@PathVariable String dept){
		Dataset<Row> result = sparkSQLService.getEmployeesByDepartment(dept);
		return result.toJSON().collectAsList();
	}

	@GetMapping("/high-earners")
	public List<String> getHighEarners(@RequestParam (defaultValue = "0") double minSalary ){
		Dataset<Row> result = sparkSQLService.getHighEarners(minSalary);
		return result.toJSON().collectAsList();
	}
	
	@GetMapping("/stats")
	public List<String> getDepartmentStats() {
		Dataset<Row> result = sparkSQLService.getDepartmentStats();
		return result.toJSON().collectAsList();
	}
	
	@GetMapping("/compensation")
	public List<String> getCompensationReport() {
		Dataset<Row> result = sparkSQLService.getCompensationReport();
		return result.toJSON().collectAsList();
	}
}
