package com.example.mydatajpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	
	//End point mapping - URI 
	@RequestMapping("/hello")
	public String hello() {
		return "<h1>Hello World!!!</h1>";
	}

	@GetMapping("/welcome")
	public String anotherHello() {
		return "<h1>Welcome to Spring Boot</h1>";
	}
	
	@GetMapping("/users")
	public List<String> getUsers() {
		List<String> users = new ArrayList<String>();
		users.add("ABCD");
		users.add("Satya");
		users.add("Mark");
		users.add("Elon");
		users.add("Sundar");
		return users;
	}
}
