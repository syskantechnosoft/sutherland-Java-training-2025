package com.example.passenger.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "passengers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column (unique = true)
	private String email;
	@Column (unique = true)
	private long mobile;
	private String gender;
	private int age;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	private int createdBy;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	private int updatedBy;
}
