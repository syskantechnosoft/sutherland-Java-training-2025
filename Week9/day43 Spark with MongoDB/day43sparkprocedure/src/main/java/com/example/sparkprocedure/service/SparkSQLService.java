package com.example.sparkprocedure.service;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class SparkSQLService {

	private final SparkSession sparkSession;

	public SparkSQLService(SparkSession sparkSession) {
		super();
		this.sparkSession = sparkSession;
	}

	public void createEmployeesStoredProcedure() {
		sparkSession.sql("DROP TABLE IF EXISTS employees");
		sparkSession.sql(
				"CREATE TABLE IF NOT EXISTS employees (id INT, name STRING, department STRING, salary DOUBLE) USING parquet");

		sparkSession.sql("INSERT INTO employees VALUES" + "(1,'ABCD', 'IT',75000)," + "(2,'XYZ', 'HR',65000),"
				+ "(3,'LMN', 'IT',85000)," + "(4,'PQR', 'Finance',70000)");
	}

	public void registerCustomFunctions() {
		sparkSession.udf().register("toUpperCase", (String s) -> s != null ? s.toUpperCase() : null,
				org.apache.spark.sql.types.DataTypes.StringType);
		sparkSession.udf().register("calculateBonus", (Double salary) -> salary != null ? salary * 0.10 : null,
				org.apache.spark.sql.types.DataTypes.DoubleType);
	}

	public Dataset<Row> getEmployeesByDepartment(String department) {
		return sparkSession.sql(
				"SELECT id, name, department, salary, calculateBonus(salary) as bonus FROM employees WHERE department ='"
						+ department + "'");

	}

	public Dataset<Row> getHighEarners(double minSalary) {
		return sparkSession.sql("SELECT toUpperCase(name) as name_upper, department, salary FROM employees"
				+ " WHERE salary > " + minSalary + " " + "ORDER BY salary DESC");
	}

	public Dataset<Row> getDepartmentStats() {
		return sparkSession.sql("SELECT department, count(*) as employee_count, avg(salary) as avg_salary,"
				+ "max(salary) as max_salary, min(salary) as min_salary " + " FROM employees GROUP BY department");
	}

	public void createTempView() {
		Dataset<Row> enrichedData = sparkSession.sql("SELECT *, calculateBonus(salary) as bonus, "
				+ "salary+calculateBonus(salary) as total_compensation FROM employees ");
		enrichedData.createOrReplaceTempView("employee_compensation");
	}
	
	public Dataset<Row> getCompensationReport() {
		return sparkSession.sql("SELECT * FROM employee_compensation");
	}
}
