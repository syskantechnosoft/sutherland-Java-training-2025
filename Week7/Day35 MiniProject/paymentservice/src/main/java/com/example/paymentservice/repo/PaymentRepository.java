package com.example.paymentservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.paymentservice.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
