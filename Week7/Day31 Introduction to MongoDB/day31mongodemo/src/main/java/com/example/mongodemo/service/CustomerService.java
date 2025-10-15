package com.example.mongodemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mongodemo.model.Customer;
import com.example.mongodemo.repo.CustomerRepository;

@Service
public class CustomerService {
	
	private CustomerRepository customerRepository;

	//Constructor Injection
	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	//findAll 
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	//findByEmail
	public Optional<Customer> findByEmail(String email){
		return customerRepository.findByEmail(email);
	}

	//save 
	public Customer save(Customer customer) {
		return customerRepository.insert(customer);
	}
	
	//update 
	public Customer update(String id, Customer customer) {
		return customerRepository.save(customer);
	}
	
	//delete 
	public void delete(String id) {
		customerRepository.deleteById(id);
	}
}
