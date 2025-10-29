package com.example.paymentservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/api/v3/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public Payment makePayment(@RequestParam String bookingId,
                               @RequestParam String userId,
                               @RequestParam Double amount,
                               @RequestParam(defaultValue = "true") boolean success) {
        return service.processPayment(bookingId, userId, amount, success);
    }
}
