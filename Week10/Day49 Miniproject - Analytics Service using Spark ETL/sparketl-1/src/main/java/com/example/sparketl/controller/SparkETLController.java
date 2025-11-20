package com.example.sparketl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparketl.service.AirlineEtlService;

@RestController
@RequestMapping("/api/etl")
public class SparkETLController {
    private final AirlineEtlService etlService;

    public SparkETLController(AirlineEtlService etlService) {
        this.etlService = etlService;
    }

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerEtl() {
        try {
            String status = etlService.runEtlPipeline();
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ETL Failed: " + e.getMessage());
        }
    }

}
