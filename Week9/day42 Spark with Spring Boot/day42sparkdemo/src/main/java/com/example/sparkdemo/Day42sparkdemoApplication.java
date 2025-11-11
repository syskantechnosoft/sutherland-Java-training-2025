package com.example.sparkdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day42sparkdemoApplication {

	public static void main(String[] args) {
		//For Access Related Issues
//		System.setProperty("spark.driver.extraJavaOptions", "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED");
//		System.setProperty("spark.executor.extraJavaOptions", "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED");

		SpringApplication.run(Day42sparkdemoApplication.class, args);
	}

}
