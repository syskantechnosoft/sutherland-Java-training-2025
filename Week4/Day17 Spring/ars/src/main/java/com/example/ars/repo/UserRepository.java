package com.example.ars.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ars.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByMobile(Long mobile);
	
	Optional<User> findByUsername(String username);
	
	
}
