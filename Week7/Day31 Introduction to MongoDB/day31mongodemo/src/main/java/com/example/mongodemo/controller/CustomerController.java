package com.example.mongodemo.controller;

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

import com.example.mongodemo.model.Customer;
import com.example.mongodemo.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private CustomerService  customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping
	public List<Customer> findAll() {
		return customerService.findAll();
	}
	
	@GetMapping("/email/{email}")
	public Customer findByEmail(@PathVariable String email) throws Exception{
		return customerService.findByEmail(email).orElseThrow(()->new Exception("Invalid Email"));
	}
	
	@PostMapping
	public Customer save(@RequestBody Customer customer) {
		return customerService.save(customer);
	}
	
	@PutMapping("/{id}")
	public Customer update(@PathVariable String id, @RequestBody Customer customer) {
		return customerService.save(customer);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		customerService.delete(id);
	}
	
//	@GetMapping("/lname/{lname}")
//	@GetMapping("/fname/{fname}")

}
