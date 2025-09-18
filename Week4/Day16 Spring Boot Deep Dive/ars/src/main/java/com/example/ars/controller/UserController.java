package com.example.ars.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ars.model.User;
import com.example.ars.service.UserService;

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
	public Optional<User> findByMobile(@PathVariable Long mobile) {
		return userService.findByMobile(mobile);
	}
	
	@GetMapping("/username/{username}")
	public Optional<User> findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}

	@PostMapping
	public User save(@RequestBody User user) {
		return userService.save(user);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable Integer id, @RequestBody User user) {
		return userService.update(id,user);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		userService.delete(id);
	}
}
