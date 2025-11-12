package com.example.sparkprocedure.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
	
	@Bean
	public SparkSession getSparkSession() {
		return SparkSession.builder()
				.appName("SpringwithSpark")
				.master("local[*]")
				.config("spark.sql.warehouse.dir","/tmp/spark-warehouse-1")
				.getOrCreate();
	}

}
