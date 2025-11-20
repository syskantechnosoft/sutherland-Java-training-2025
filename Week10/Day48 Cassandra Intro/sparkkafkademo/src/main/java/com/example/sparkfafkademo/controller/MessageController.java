package com.example.sparkfafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparkfafkademo.service.ProducerService;

// --- REST Controller to trigger messages ---
@RestController
public class MessageController {

    @Autowired
    private ProducerService producerService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        producerService.sendMessage(message);
        return "Message sent to Kafka! Check console for Spark output.";
    }
}
