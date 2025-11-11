package com.example.sparkdemo.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

	@Bean
	public SparkSession createSparkSession() {
		return SparkSession.builder().appName("SpringBootSparkDemo").master("local[*]").getOrCreate();
	}

}
