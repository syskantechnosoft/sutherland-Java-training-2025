package com.example.booking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.booking.model.Booking;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class BookingControllerTest {
	@Container
	static MongoDBContainer mongo = new MongoDBContainer("mongo:6.0.5");

	@DynamicPropertySource
	static void mongoProperties(DynamicPropertyRegistry r) {
		r.add("spring.data.mongodb.uri", mongo::getReplicaSetUrl);
	}

	@Autowired
	TestRestTemplate rest;

	@Test
	void createAndGetBooking() {
		Booking b = new Booking();
		b.setUserId("u1");
		b.setFlightId("f1");
		b.setSeats(1);
		ResponseEntity<Booking> resp = rest.postForEntity("/api/bookings", b, Booking.class);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		assertNotNull(resp.getBody().getId());
	}
}
