package com.example.springkafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class TelemetryController {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public TelemetryController(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	@PostMapping("/publish")
	public String publish(@RequestBody String payload) {
		kafkaTemplate.send("telemetry-topic",payload);
		return "OK";
	}
	
}
