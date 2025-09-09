package com.example.mydatajpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mydatajpa.model.User;
import com.example.mydatajpa.repo.UserRepository;

@Service
public class UserService {
	//@Autowired // Setter Injection SI
	private UserRepository userRepository;

	//Constructor Injection CI
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(int id){
		return userRepository.findById(id);
	}
	
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}

	public Optional<User> findByMobile(Long mobile){
		return userRepository.findByMobile(mobile);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User update(int id, User user) {
		return userRepository.save(user);
	}
	
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

}
