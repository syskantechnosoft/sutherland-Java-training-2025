package com.example.sparkkafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.sparkkafka.model.UserEvent;

@Service
public class KafkaConsumerService {
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    
    @KafkaListener(topics = "${kafka.topic.input}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(UserEvent event) {
        logger.info("Consumed message from Kafka: {}", event);
        
        // Process the event
        processEvent(event);
    }
    
    private void processEvent(UserEvent event) {
        // Business logic here
        logger.info("Processing event for user: {} with type: {}", 
            event.getUserId(), event.getEventType());
    }
    
    @KafkaListener(topics = "${kafka.topic.output}", groupId = "processed-group")
    public void consumeProcessedMessage(String message) {
        logger.info("Consumed processed message from Spark: {}", message);
    }
}

