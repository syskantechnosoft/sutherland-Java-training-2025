package com.example.paymentservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "booking-service", url = "http://localhost:8085/api/v3/bookings")
public interface BookingClient {

    // Calls booking confirmation endpoint
    @PostMapping("/{id}/confirm")
    void confirmBooking(@PathVariable("id") String id);
}