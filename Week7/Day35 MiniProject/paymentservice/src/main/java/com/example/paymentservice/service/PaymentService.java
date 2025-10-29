package com.example.paymentservice.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.example.paymentservice.client.BookingClient;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repo.PaymentRepository;

@Service
public class PaymentService {

	private final PaymentRepository repo;
	private final BookingClient bookingClient;

	public PaymentService(PaymentRepository repo, BookingClient bookingClient) {
		this.repo = repo;
		this.bookingClient = bookingClient;
	}

	public Payment processPayment(String bookingId, String userId, Double amount, boolean success) {
		Payment payment = Payment.builder().bookingId(bookingId).userId(userId).amount(amount)
				.status(success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED).paymentDate(Instant.now()).build();

		repo.save(payment);

		// If successful, confirm booking
		if (success) {
			try {
				bookingClient.confirmBooking(bookingId);
			} catch (Exception e) {
				// log, don't break payment flow
				System.err.println("Booking confirmation failed: " + e.getMessage());
			}
		}

		return payment;
	}
}
