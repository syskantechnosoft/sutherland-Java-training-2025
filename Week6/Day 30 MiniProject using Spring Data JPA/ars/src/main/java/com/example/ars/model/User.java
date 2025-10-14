package com.example.ars.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//Normal Bean Class 
//Entity Bean Class
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(length = 50, nullable = false)
	private String hashedPassword;
	@Column(unique = true, length = 250, nullable = false)
	private String email;
	@Column(unique = true)
	private Long mobile;
	// Audit Logs/ Fields/properties
	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private LocalDateTime createdAt;
	private Integer createdBy;
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	private Integer updatedBy;

}
