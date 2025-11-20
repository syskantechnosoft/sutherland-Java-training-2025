package com.example.sparkfafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//--- Simple Service to send messages to Kafka ---
@Service
public class ProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.topic.name}")
	private String topicName;

	public void sendMessage(String msg) {
		kafkaTemplate.send(topicName, msg);
		System.out.println("Published to Kafka: " + msg);
	}
}
