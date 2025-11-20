package com.example.sparketl.config;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

	@Bean
	public SparkSession sparkSession() {
		// Spark 4.0 Configuration
		SparkConf conf = new SparkConf().setAppName("AirlineReservationETL").setMaster("local[*]") 
				// Use all available cores
				.set("spark.driver.memory", "2g").set("spark.executor.memory", "2g")
				// Spark 4 optimization: Use ANSI SQL mode by default
				.set("spark.sql.ansi.enabled", "true");

		return SparkSession.builder().config(conf).getOrCreate();
	}
}
