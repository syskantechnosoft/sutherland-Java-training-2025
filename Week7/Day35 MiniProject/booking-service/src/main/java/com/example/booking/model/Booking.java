package com.example.booking.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.booking.enums.BookingStatus;
import com.example.booking.enums.SeatClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document (collection = "bookings") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	@Id
	private String id;
	private String userId;
	private String flightId;
	private SeatClass seatClass; //BUSINESS, ECONOMY
	private int seats;
	private BookingStatus bookingStatus;
	private BigDecimal amount;
	private Instant createdAt;
	
}
