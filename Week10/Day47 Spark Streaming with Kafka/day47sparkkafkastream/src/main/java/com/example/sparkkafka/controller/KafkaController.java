package com.example.sparkkafka.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparkkafka.model.UserEvent;
import com.example.sparkkafka.service.KafkaProducerService;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    
    private final KafkaProducerService producerService;
    
    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }
    
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody UserEvent event) {
        producerService.sendMessage(event);
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Event sent to Kafka");
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/send-batch")
    public ResponseEntity<Map<String, String>> sendBatchMessages(@RequestParam(defaultValue = "10") int count) {
        producerService.sendMultipleMessages(count);
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", count + " events sent to Kafka");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Kafka-Spark Integration");
        
        return ResponseEntity.ok(response);
    }
}

