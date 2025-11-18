package com.example.sparkkafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserEvent implements Serializable {
    
    @JsonProperty("userId")
    private String userId;
    
    @JsonProperty("eventType")
    private String eventType;
    
    @JsonProperty("eventValue")
    private Double eventValue;
    
    @JsonProperty("timestamp")
    private String timestamp;
    
    public UserEvent() {
        this.timestamp = LocalDateTime.now().toString();
    }
    
    public UserEvent(String userId, String eventType, Double eventValue) {
        this.userId = userId;
        this.eventType = eventType;
        this.eventValue = eventValue;
        this.timestamp = LocalDateTime.now().toString();
    }
}