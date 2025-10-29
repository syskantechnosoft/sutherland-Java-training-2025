package com.example.booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.booking.model.Booking;
import com.example.booking.repo.BookingRepository;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
	@Mock
	BookingRepository repo;
	@InjectMocks
	BookingService service;

	@Test
	void create_setsPendingAndTimestamp() {
		Booking b = new Booking();
		b.setUserId("u1");
		when(repo.save(any())).thenAnswer(inv -> {
			Booking arg = inv.getArgument(0);
			arg.setId("id1");
			return arg;
		});

		Booking saved = service.create(b);
		assertEquals("PENDING", saved.getBookingStatus());
		assertNotNull(saved.getCreatedAt());
		assertEquals("id1", saved.getId());
	}
}
