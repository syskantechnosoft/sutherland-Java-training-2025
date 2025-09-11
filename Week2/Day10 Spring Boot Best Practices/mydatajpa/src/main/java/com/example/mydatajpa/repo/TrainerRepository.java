package com.example.mydatajpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mydatajpa.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
	public Trainer findByEmail(String email);
}
