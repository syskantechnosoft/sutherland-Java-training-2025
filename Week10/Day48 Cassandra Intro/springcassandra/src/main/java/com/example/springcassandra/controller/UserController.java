package com.example.springcassandra.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcassandra.model.User;
import com.example.springcassandra.service.UserService;

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
		return userService.findAllUsers();
	}

	@GetMapping("/{id}")
	public Optional<User> findByUserId(@PathVariable String id) {
		return userService.findUserById(id);
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user.getFirstName(), user.getLastName());
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}

}
