package com.example.sparkdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparkdemo.service.SparkWordCountService;

@RestController
@RequestMapping("/api/spark")
public class SparkController {
	
	private final SparkWordCountService sparkWordCountService;

	public SparkController(SparkWordCountService sparkWordCountService) {
		super();
		this.sparkWordCountService = sparkWordCountService;
	}
	
	@PostMapping("/words")
	public List<String> getWords(@RequestBody List<String> input){
		return sparkWordCountService.getWordCount(input);
	}

}
