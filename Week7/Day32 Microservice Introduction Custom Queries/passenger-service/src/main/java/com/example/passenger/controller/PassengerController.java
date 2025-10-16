package com.example.passenger.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passenger.model.Passenger;
import com.example.passenger.service.PassengerService;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
	
	private PassengerService passengerService;

	public PassengerController(PassengerService passengerService) {
		super();
		this.passengerService = passengerService;
	}
	
	@GetMapping
	public List<Passenger> findAll() {
		return passengerService.findAll();		
	}
	
	@GetMapping("/{id}")
	public Optional<Passenger> findById(@PathVariable int id){
		return passengerService.findById(id);
	}
	
	@GetMapping("/email/{email}")
	public Optional<Passenger> findByEmail(@PathVariable String email){
		return passengerService.findByEmail(email);
	}
	
	@GetMapping("/mobile/{mobile}")
	public Optional<Passenger> findByMobile(@PathVariable long mobile){
		return passengerService.findByMobile(mobile);
	}
	
	@PostMapping
	public Passenger save(@RequestBody Passenger passenger) {
		return passengerService.save(passenger);
	}
	
	@PutMapping("/{id}")
	public Passenger update(@PathVariable int id, Passenger passenger) {
		return passengerService.update(id, passenger);
	}

}
