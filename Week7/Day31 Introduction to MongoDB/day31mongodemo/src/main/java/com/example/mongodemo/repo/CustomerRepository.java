package com.example.mongodemo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodemo.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	List<Customer> findByFirstName(String firstName);
	List<Customer> findByLastName (String lastName);
	Optional<Customer> findByEmail(String email);
}
