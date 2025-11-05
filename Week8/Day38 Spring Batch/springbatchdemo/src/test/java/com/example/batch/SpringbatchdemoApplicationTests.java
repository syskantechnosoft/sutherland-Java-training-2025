package com.example.batch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class SpringbatchdemoApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils() {
	    return new JobLauncherTestUtils();
	}

}
