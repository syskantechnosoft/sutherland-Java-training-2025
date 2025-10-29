package com.example.booking.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.booking.enums.BookingStatus;
import com.example.booking.model.Booking;
import com.example.booking.repo.BookingRepository;

@Service
public class BookingService {
	
	private BookingRepository bookingRepository;

	public BookingService(BookingRepository bookingRepository) {
		super();
		this.bookingRepository = bookingRepository;
	}
	
	public Booking create(Booking booking) {	
		booking.setBookingStatus(BookingStatus.PENDING);
		booking.setCreatedAt(Instant.now());
		return bookingRepository.save(booking);
	}
	
	public Optional<Booking> findById(String id){
		return bookingRepository.findById(id);
	}
	
	public Booking confirm(String id) {
		Booking booking = bookingRepository.findById(id).orElseThrow();
		booking.setBookingStatus(BookingStatus.CONFIRMED);
		return bookingRepository.save(booking);
	}

	public List<Booking> findByUserId(String userId){
		return bookingRepository.findByUserId(userId);
	}
	
	public List<Booking> findByFlightId(String flightId){
		return bookingRepository.findByFlightId(flightId);
	}
	
	public void deleteById(String id) {
		bookingRepository.deleteById(id);
	}
}
