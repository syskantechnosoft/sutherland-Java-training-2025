package com.example.datajpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.datajpa.entity.Passenger;
import com.example.datajpa.repo.PassengerRepository;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
	
	
	private PassengerRepository passengerRepository;

	//Constructor Injection (Recommended approach)
	public PassengerController(PassengerRepository passengerRepository) {
		super();
		this.passengerRepository = passengerRepository;
	}
	
	@GetMapping
	public List<Passenger> findAll() {
		return passengerRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Passenger> findById(@PathVariable int id) {
		return passengerRepository.findById(id);
	}

	@PostMapping //Entity will not have id property
	public Passenger save(@RequestBody Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	@PutMapping("/{id}") //Entity should have id property
	public Passenger update(@PathVariable int id, @RequestBody Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById (@PathVariable int id) {
		passengerRepository.deleteById(id);
	}
}
