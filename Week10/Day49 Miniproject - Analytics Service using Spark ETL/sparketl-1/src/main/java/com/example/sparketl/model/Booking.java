package com.example.sparketl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	private String bookingId;
	private String flightId;
	private String passengerName;
	private String seatClass;
}
