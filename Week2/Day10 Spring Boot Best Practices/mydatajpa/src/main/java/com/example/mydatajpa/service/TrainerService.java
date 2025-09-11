package com.example.mydatajpa.service;

import java.util.List;
import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mydatajpa.model.Trainer;
import com.example.mydatajpa.repo.TrainerRepository;

@Service
public class TrainerService {
//	@Autowired (Setter Injection)
	private TrainerRepository trainerRepository;

// Constructor Injection 
	public TrainerService(TrainerRepository trainerRepository) {
		super();
		this.trainerRepository = trainerRepository;
	}
	
	
	//CRUD Operation
	//Rea-All operation
	public List<Trainer> findAll(){
		return trainerRepository.findAll();
	}
	
	//Read by ID opration
	public Optional<Trainer> findById(int id){
		return trainerRepository.findById(id);
	}
	
	
	//save operation
	
	public Trainer save(Trainer trainer){
		return trainerRepository.save(trainer);
	}
	
	//Update Operation
	
	public Trainer update(int id, Trainer trainer){
		return trainerRepository.save(trainer);
	}
	
	//Delete opearation
	public void deleteById (int id) {
		trainerRepository.deleteById(id);
	}
	
	

}
