package com.example.mydatajpa.controller;

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

import com.example.mydatajpa.model.User;
import com.example.mydatajpa.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<User> findById(@PathVariable Integer id) {
		return userService.findById(id);
	}

	@GetMapping("/email/{email}")
	public Optional<User> findByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}
	
	@GetMapping("/mobile/{mobile}")
	public Optional<User> findByMobilr(@PathVariable Long mobile) {
		return userService.findByMobile(mobile);
	}
	
	@PostMapping
	public User save(@RequestBody User user) {
		return userService.save(user);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable int id, @RequestBody User user) {
		return userService.save(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		userService.deleteById(id);
	}
	
}
