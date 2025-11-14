package com.example.sparkmysql.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparkmysql.service.SparkEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private SparkEmployeeService sparkEmployeeService;

    /**
     * Get all employees using Spark
     * GET /api/employees
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllEmployees() {
        List<Map<String, Object>> employees = 
            sparkEmployeeService.convertDatasetToList(
                sparkEmployeeService.loadEmployeesFromMySQL()
            );
        return ResponseEntity.ok(employees);
    }

    /**
     * Get employees by department using stored procedure
     * GET /api/employees/department/{department}
     */
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Map<String, Object>>> getEmployeesByDepartment(
            @PathVariable String department) {
        List<Map<String, Object>> employees = 
            sparkEmployeeService.getEmployeesByDepartmentUsingStoredProc(department);
        return ResponseEntity.ok(employees);
    }

    /**
     * Get employees with UDF transformations
     * GET /api/employees/with-bonus?bonus=10
     */
    @GetMapping("/with-bonus")
    public ResponseEntity<List<Map<String, Object>>> getEmployeesWithBonus(
            @RequestParam(defaultValue = "10") double bonus) {
        List<Map<String, Object>> employees = 
            sparkEmployeeService.getEmployeesWithUDFs(bonus);
        return ResponseEntity.ok(employees);
    }

    /**
     * Get department salary statistics
     * GET /api/employees/department-stats
     */
    @GetMapping("/department-stats")
    public ResponseEntity<List<Map<String, Object>>> getDepartmentStats() {
        List<Map<String, Object>> stats = 
            sparkEmployeeService.getDepartmentSalaryStats();
        return ResponseEntity.ok(stats);
    }

    /**
     * Execute custom SQL query
     * POST /api/employees/query
     * Body: { "sql": "SELECT * FROM employees WHERE salary > 70000" }
     */
    @PostMapping("/query")
    public ResponseEntity<List<Map<String, Object>>> executeCustomQuery(
            @RequestBody Map<String, String> request) {
        String sql = request.get("sql");
        List<Map<String, Object>> result = 
            sparkEmployeeService.executeCustomQuery(sql);
        return ResponseEntity.ok(result);
    }
}