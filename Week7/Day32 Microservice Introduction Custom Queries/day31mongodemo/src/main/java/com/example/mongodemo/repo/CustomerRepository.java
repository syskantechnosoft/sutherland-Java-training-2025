package com.example.mongodemo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mongodemo.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	List<Customer> findByFirstName(String firstName);
	List<Customer> findByLastName (String lastName);
	Optional<Customer> findByEmail(String email);
	
	List<Customer> findByCity(String city);
	
	@Query("{'age': {$gt:?0}}")
	List<Customer> findCustomersOlderThan(int age);
	
	@Query("{ 'city': ?0, 'age': { $lt: ?1 } }")
	List<Customer> findByCityAndYoungerThan(String city, int age);

	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
	List<Customer> findByNameLike(String namePattern);


}
