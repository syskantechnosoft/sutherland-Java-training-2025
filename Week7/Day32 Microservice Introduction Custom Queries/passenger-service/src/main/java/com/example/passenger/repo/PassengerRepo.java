package com.example.passenger.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passenger.model.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Integer> {

	
	Optional<Passenger> findByEmail(String email);
	
	List<Passenger> findByName(String name);
	
	Optional<Passenger> findByMobile(Long Mobile);
	
	
	
}
