package com.example.mydatajpa.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mydatajpa.model.User;

@Repository //It's optional
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByMobile(Long mobile);
}
