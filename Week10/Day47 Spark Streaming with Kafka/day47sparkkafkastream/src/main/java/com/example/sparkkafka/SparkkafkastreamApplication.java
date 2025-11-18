package com.example.sparkkafka;

import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SparkkafkastreamApplication {

	public static void main(String[] args) {
		
		System.out.println("Current Timestamp:" + Instant.now());
		SpringApplication.run(SparkkafkastreamApplication.class, args);
	}

}
