package com.example.sparketl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
	private String flightId;
	private String origin;
	private String destination;
	private double basePrice;
}