package com.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSQLExample {
	public static void main(String[] args) {

		// 1. Create Spark Session
		SparkSession spark = SparkSession.builder().appName("Spark SQL with Java Example").master("local[*]")
				// runs locally using all CPU cores
				.getOrCreate();

		// 2. Load Data (example CSV)
		Dataset<Row> df = spark.read().option("header", "true") // first line as header
				.option("inferSchema", "true").csv("d:/employees.csv"); // your CSV file path

		// 3. Show Data
		df.show();

		// 4. Register DataFrame as Temporary SQL View
		df.createOrReplaceTempView("employees");

		// 5. Run SQL Query
		Dataset<Row> result = spark.sql("SELECT department, AVG(salary) AS avg_salary " + "FROM employees "
				+ "GROUP BY department " + "ORDER BY avg_salary DESC");

		// 6. Display Result
		result.show();

		Dataset<Row> users = spark.read().format("jdbc")
				.option("url", "jdbc:mysql://localhost:3306/arsdb").option("dbtable", "users")
				.option("user", "root").option("password", "root").option("driver", "com.mysql.cj.jdbc.Driver")
				.load();

		users.createOrReplaceTempView("users");
		spark.sql("SELECT COUNT(*) FROM users").show();

		// 7. Stop Spark Session
		spark.stop();
	}
}
