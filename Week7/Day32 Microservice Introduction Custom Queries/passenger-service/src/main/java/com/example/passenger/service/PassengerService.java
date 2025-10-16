package com.example.passenger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.passenger.model.Passenger;
import com.example.passenger.repo.PassengerRepo;

@Service
public class PassengerService {
	
	private PassengerRepo passengerRepo;

	public PassengerService(PassengerRepo passengerRepo) {
		super();
		this.passengerRepo = passengerRepo;
	}
	
	public List<Passenger> findAll() {
		return passengerRepo.findAll();
	}
	
	public Optional<Passenger> findById(int id){
		return passengerRepo.findById(id);
	}

	public Optional<Passenger> findByEmail(String email){
		return passengerRepo.findByEmail(email);
	}
	
	public Optional<Passenger> findByMobile(long mobile){
		return passengerRepo.findByMobile(mobile);
	}
	
	public Passenger save(Passenger passenger) {
		return passengerRepo.save(passenger);
	}
	
	public Passenger update(int id, Passenger passenger) {
		return passengerRepo.save(passenger);
	}
}
