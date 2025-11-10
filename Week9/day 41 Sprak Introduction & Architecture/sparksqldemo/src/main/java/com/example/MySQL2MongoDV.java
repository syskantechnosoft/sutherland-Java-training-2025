package com.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class MySQL2MongoDV {
	public static void main(String[] args) {
		// 1. Create Spark Session
		SparkSession spark = SparkSession.builder().appName("MySQL to MongoDB ETL").master("local[*]")
				// MongoDB connection options
				.config("spark.mongodb.write.connection.uri", "mongodb://localhost:27017")
				.config("spark.mongodb.write.database", "ars_etl")
				.config("spark.mongodb.write.collection", "processed_users").getOrCreate();

		// 2. Read data from MySQL
		Dataset<Row> mysqlData = spark.read().format("jdbc").option("url", "jdbc:mysql://localhost:3306/arsdb")
				.option("driver", "com.mysql.cj.jdbc.Driver").option("dbtable", "users").option("user", "root")
				.option("password", "root").load();

		System.out.println("✅ Read from MySQL:");
		mysqlData.show(5);

		// 3. Register as temporary SQL view
		mysqlData.createOrReplaceTempView("users");

		// 4. Transform with Spark SQL
		Dataset<Row> transformed = spark
				.sql("SELECT id, UPPER(username) AS username, email FROM users WHERE email IS NOT NULL ");

		System.out.println("✅ After transformation:");
		transformed.show(5);

		// 5. Write transformed data to MongoDB
		transformed.write().format("mongodb").mode("overwrite").save();

		System.out.println("✅ Data written to MongoDB collection 'processed_users'");

		// 6. Stop Spark
		spark.stop();
	}
}
