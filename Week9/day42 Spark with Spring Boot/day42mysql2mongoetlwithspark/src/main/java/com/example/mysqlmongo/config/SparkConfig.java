package com.example.mysqlmongo.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Value("${mongo.uri}")
    private String mongoUri;

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName("SpringBootSparkETL")
                .master("local[*]")
                .config("spark.mongodb.write.connection.uri", mongoUri)
                .getOrCreate();
    }
}

