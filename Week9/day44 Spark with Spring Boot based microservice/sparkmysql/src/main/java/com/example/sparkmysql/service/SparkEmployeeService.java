package com.example.sparkmysql.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sparkmysql.config.SparkConfig;

@Service
public class SparkEmployeeService {

    @Autowired
    private SparkSession sparkSession;

    @Autowired
    private JavaSparkContext javaSparkContext;

    @Autowired
    private SparkConfig sparkConfig;

    /**
     * Load employees from MySQL using Spark SQL
     */
    public Dataset<Row> loadEmployeesFromMySQL() {
        return sparkSession.read()
                .format("jdbc")
                .option("url", sparkConfig.getMysqlUrl())
                .option("dbtable", "employees")
                .option("user", sparkConfig.getMysqlUser())
                .option("password", sparkConfig.getMysqlPassword())
                .option("driver", sparkConfig.getMysqlDriver())
                .load();
    }

    /**
     * Execute a stored procedure to get employees by department
     */
    public List<Map<String, Object>> getEmployeesByDepartmentUsingStoredProc(String department) {
        String query = String.format("(CALL get_employees_by_department('%s')) AS dept_employees", department);
        
        Dataset<Row> df = sparkSession.read()
                .format("jdbc")
                .option("url", sparkConfig.getMysqlUrl())
                .option("dbtable", query)
                .option("user", sparkConfig.getMysqlUser())
                .option("password", sparkConfig.getMysqlPassword())
                .option("driver", sparkConfig.getMysqlDriver())
                .load();

        return convertDatasetToList(df);
    }

    /**
     * Register and use User Defined Functions (UDFs)
     */
    public void registerUDFs() {
        // UDF to calculate annual salary with bonus
        UDF2<Double, Double, Double> calculateAnnualSalary = 
            (salary, bonusPercent) -> salary * 12 * (1 + bonusPercent / 100);
        
        sparkSession.udf().register("calculate_annual_salary", calculateAnnualSalary, DataTypes.DoubleType);

        // UDF to get full name
        UDF2<String, String, String> getFullName = 
            (firstName, lastName) -> firstName + " " + lastName;
        
        sparkSession.udf().register("get_full_name", getFullName, DataTypes.StringType);

        // UDF to categorize salary
        UDF1<Double, String> categorizeSalary = salary -> {
            if (salary < 50000) return "Entry Level";
            else if (salary < 80000) return "Mid Level";
            else if (salary < 120000) return "Senior Level";
            else return "Executive Level";
        };
        
        sparkSession.udf().register("categorize_salary", categorizeSalary, DataTypes.StringType);
    }

    /**
     * Use UDFs to transform employee data
     */
    public List<Map<String, Object>> getEmployeesWithUDFs(double bonusPercent) {
        registerUDFs();
        
        Dataset<Row> employees = loadEmployeesFromMySQL();
        employees.createOrReplaceTempView("employees");

        String query = String.format(
            "SELECT id, " +
            "get_full_name(first_name, last_name) as full_name, " +
            "email, department, salary, " +
            "calculate_annual_salary(salary, %.2f) as annual_salary_with_bonus, " +
            "categorize_salary(salary) as salary_category " +
            "FROM employees " +
            "WHERE is_active = true",
            bonusPercent
        );

        Dataset<Row> result = sparkSession.sql(query);
        return convertDatasetToList(result);
    }

    /**
     * Get department salary statistics using Spark aggregations
     */
    public List<Map<String, Object>> getDepartmentSalaryStats() {
        Dataset<Row> employees = loadEmployeesFromMySQL();
        employees.createOrReplaceTempView("employees");

        String query = 
            "SELECT department, " +
            "COUNT(*) as employee_count, " +
            "AVG(salary) as avg_salary, " +
            "MIN(salary) as min_salary, " +
            "MAX(salary) as max_salary, " +
            "SUM(salary) as total_salary " +
            "FROM employees " +
            "WHERE is_active = true " +
            "GROUP BY department " +
            "ORDER BY avg_salary DESC";

        Dataset<Row> result = sparkSession.sql(query);
        return convertDatasetToList(result);
    }

    /**
     * Execute custom SQL query with UDF
     */
    public List<Map<String, Object>> executeCustomQuery(String sqlQuery) {
        registerUDFs();
        Dataset<Row> employees = loadEmployeesFromMySQL();
        employees.createOrReplaceTempView("employees");
        
        Dataset<Row> result = sparkSession.sql(sqlQuery);
        return convertDatasetToList(result);
    }

    /**
     * Convert Spark Dataset to List of Maps for JSON response
     */
    public List<Map<String, Object>> convertDatasetToList(Dataset<Row> dataset) {
        List<Map<String, Object>> result = new ArrayList<>();
        
        dataset.collectAsList().forEach(row -> {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < row.size(); i++) {
                String fieldName = dataset.columns()[i];
                Object value = row.get(i);
                map.put(fieldName, value);
            }
            result.add(map);
        });
        
        return result;
    }

    /**
     * Save transformed data back to MySQL
     */
    public void saveEmployeesToMySQL(Dataset<Row> dataset, String tableName) {
        dataset.write()
                .format("jdbc")
                .option("url", sparkConfig.getMysqlUrl())
                .option("dbtable", tableName)
                .option("user", sparkConfig.getMysqlUser())
                .option("password", sparkConfig.getMysqlPassword())
                .option("driver", sparkConfig.getMysqlDriver())
                .mode(SaveMode.Append)
                .save();
    }
}