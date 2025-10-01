package com.example.ars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ars.model.User;
import com.example.ars.repo.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User update(Integer id, User user) {
		return userRepository.save(user);
	}
	
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	public Optional<User> findByMobile(Long mobile){
		return userRepository.findByMobile(mobile);
	}
}
