package com.example.booking.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final JwtUtil jwtUtil;

	public AuthController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
		String username = credentials.get("username");
		String password = credentials.get("password");

// Simplified: in real setup, validate against Keycloak or database
		if ("admin".equals(username) && "admin123".equals(password)) {
			String token = jwtUtil.generateToken(username, "ADMIN");
			return ResponseEntity.ok(Map.of("token", token));
		}

		return ResponseEntity.status(401).body("Invalid credentials");
	}
}
