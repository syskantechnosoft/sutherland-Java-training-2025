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

import com.example.mydatajpa.model.Trainer;
import com.example.mydatajpa.service.TrainerService;

@RestController
@RequestMapping("api/v1/trainers")
public class TrainerController {

	private TrainerService trainerService;

	
	// Constructor Injection
	public TrainerController(TrainerService trainerService) {
		super();
		this.trainerService = trainerService;
	}

	// CRUD Operation
	// Rea-All operation
	@GetMapping
	public List<Trainer> findAll() {
		return trainerService.findAll();
	}

	// Read by ID opration
	@GetMapping("/{id}")
	public Optional<Trainer> findById(@PathVariable int id) {
		return trainerService.findById(id);
	}

	// save operation

	@PostMapping
	public Trainer save(@RequestBody Trainer trainer) {
		return trainerService.save(trainer);
	}

	// Update Operation
	@PutMapping("/{id}")
	public Trainer update(@PathVariable int id, @RequestBody Trainer trainer) {
		return trainerService.update(id, trainer);
	}

	// Delete opearation
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		trainerService.deleteById(id);
	}

}
