package com.example.sparkkafka.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.sparkkafka.model.UserEvent;

@Service
public class KafkaProducerService {
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    
    @Value("${kafka.topic.input}")
    private String topicName;
    
    public KafkaProducerService(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendMessage(UserEvent event) {
        logger.info("Sending message to topic: {} - Event: {}", topicName, event);
        
        CompletableFuture<SendResult<String, UserEvent>> future = 
            kafkaTemplate.send(topicName, event.getUserId(), event);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Message sent successfully: offset = {}, partition = {}", 
                    result.getRecordMetadata().offset(),
                    result.getRecordMetadata().partition());
            } else {
                logger.error("Failed to send message: {}", ex.getMessage());
            }
        });
    }
    
    public void sendMultipleMessages(int count) {
        String[] eventTypes = {"LOGIN", "PURCHASE", "VIEW", "LOGOUT", "CLICK"};
        
        for (int i = 0; i < count; i++) {
            String userId = "user_" + (i % 10);
            String eventType = eventTypes[i % eventTypes.length];
            Double eventValue = Math.random() * 1000;
            
            UserEvent event = new UserEvent(userId, eventType, eventValue);
            sendMessage(event);
            
            try {
                Thread.sleep(100); // Small delay between messages
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

