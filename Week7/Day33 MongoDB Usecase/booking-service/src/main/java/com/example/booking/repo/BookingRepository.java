package com.example.booking.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.booking.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
	List<Booking> findByUserId(String userId);
	List<Booking> findByFlightId(String flightId);
}
