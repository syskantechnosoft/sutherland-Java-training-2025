package com.example.mysqlmongo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mysqlmongo.service.SparkETLService;

@RestController
public class SparkETLController {

    private final SparkETLService etlService;

    public SparkETLController(SparkETLService etlService) {
        this.etlService = etlService;
    }

    @GetMapping("/spark/etl/run")
    public String runETL() {
        return etlService.transferData();
    }
}

