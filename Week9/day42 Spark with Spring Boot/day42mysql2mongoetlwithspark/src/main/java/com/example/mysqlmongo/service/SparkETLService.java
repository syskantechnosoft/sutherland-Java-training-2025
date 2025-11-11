package com.example.mysqlmongo.service;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.*;

@Service
public class SparkETLService {

    private final SparkSession spark;

    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUser;

    @Value("${spring.datasource.password}")
    private String mysqlPass;

    public SparkETLService(SparkSession spark) {
        this.spark = spark;
    }

    public String transferData() {
        // 1. Read from MySQL
        Dataset<Row> employees = spark.read()
                .format("jdbc")
                .option("url", mysqlUrl)
                .option("dbtable", "employees")
                .option("user", mysqlUser)
                .option("password", mysqlPass)
                .load();

        // 2. Simple Transformation (filter + column rename)
//        Dataset<Row> transformed = employees
//                .filter("salary > 70000")
//                .withColumnRenamed("name", "employee_name");
//
//        // 3. Write to MongoDB
//        transformed.write()
//                .format("mongodb")
//                .mode("overwrite")
//                .option("database", "hrdb")
//                .option("collection", "high_earners")
//                .save();
        
     // 2️⃣ Transformation using SQL
        employees.createOrReplaceTempView("employees");
        Dataset<Row> filtered = spark.sql("""
                SELECT id, name, department, salary
                FROM employees
                WHERE salary > 65000
                """);

        // 3️⃣ Register UDF for bonus
        UserDefinedFunction calcBonus = udf(
                (UDF1<String, Double>) dept -> dept.equalsIgnoreCase("IT") ? 0.10 : 0.05,
                DataTypes.DoubleType
        );
        spark.udf().register("calcBonus", calcBonus);

        // 4️⃣ Add derived columns
        Dataset<Row> transformed = filtered
                .withColumn("bonus_percent", callUDF("calcBonus", col("department")))
                .withColumn("bonus_amount", expr("salary * bonus_percent"))
                .withColumn("is_senior", expr("salary > 85000"));

        // 5️⃣ Compute department averages
        Dataset<Row> deptAvg = transformed.groupBy("department")
                .agg(avg("salary").alias("avg_salary"))
                .withColumnRenamed("department", "dept");

        // 6️⃣ Join with main data
        Dataset<Row> enriched = transformed.join(
                deptAvg,
                transformed.col("department").equalTo(deptAvg.col("dept")),
                "left"
        ).drop("dept");

        // 7️⃣ Write to MongoDB
        enriched.write()
                .format("mongodb")
                .mode("overwrite")
                .option("database", "hrdb")
                .option("collection", "employees_enriched")
                .save();

        return "Data transferred successfully to MongoDB!";
    }
}

