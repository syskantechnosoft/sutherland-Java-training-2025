package com.example.booking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.model.Booking;
import com.example.booking.service.BookingService;

@RestController
@RequestMapping("/api/v3/bookings")
public class BookingController {

	private BookingService bookingService;

	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}
	
	@GetMapping("/{id}")
	public Optional<Booking> findById(@PathVariable String id){
		return bookingService.findById(id);
	}
	
	
	@GetMapping("/user/{userId}")
	public List<Booking> findByUserId(@PathVariable String userId){
		return bookingService.findByUserId(userId);
	}
	
	@GetMapping("/flight/{flightId}")
	public List<Booking> findByFlightId(@PathVariable String flightId){
		return bookingService.findByFlightId(flightId);
	}
	
	@PostMapping
	public ResponseEntity<Booking> create(@RequestBody Booking booking) {
		Booking savedBooking = bookingService.create(booking);
		return ResponseEntity.ok(savedBooking);
	}
	
	@PostMapping("/{id}/confirm")
	public ResponseEntity<Booking> confirm(@PathVariable String id) {
		return ResponseEntity.ok(bookingService.confirm(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		bookingService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
}
