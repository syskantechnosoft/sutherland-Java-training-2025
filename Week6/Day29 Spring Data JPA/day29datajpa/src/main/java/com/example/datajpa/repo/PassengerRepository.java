package com.example.datajpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.datajpa.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
