package com.example.mongodemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.mongodemo.model.Customer;
import com.example.mongodemo.repo.CustomerRepository;

@SpringBootApplication
public class Day31mongodemoApplication implements CommandLineRunner {
	
	@Autowired //Field or property Injection
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Day31mongodemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repository.deleteAll();

	    // save a couple of customers
//	    repository.save(new Customer("Alice", "Smith","alice.smith@gmail.com"));
//	    repository.save(new Customer("Bob", "Smith","bob.smith@gmail.com"));
//
//	    // fetch all customers
//	    System.out.println("Customers found with findAll():");
//	    System.out.println("-------------------------------");
//	    for (Customer customer : repository.findAll()) {
//	      System.out.println(customer);
//	    }
//	    System.out.println();
//
//	    // fetch an individual customer
//	    System.out.println("Customer found with findByFirstName('Alice'):");
//	    System.out.println("--------------------------------");
//	    System.out.println(repository.findByFirstName("Alice"));
//
//	    System.out.println("Customers found with findByLastName('Smith'):");
//	    System.out.println("--------------------------------");
//	    for (Customer customer : repository.findByLastName("Smith")) {
//	      System.out.println(customer);
//	    }
		
	}

}
