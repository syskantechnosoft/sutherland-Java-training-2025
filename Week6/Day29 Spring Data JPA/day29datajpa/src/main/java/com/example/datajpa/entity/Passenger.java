package com.example.datajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data //(default constructors with getters & setters) 
@Table (name = "tbl_passenger")
public class Passenger {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	private int id;
	@Column(length = 50, insertable = true, nullable = false, name = "pass_name")
	private String name;
	@Column (unique = true)
	private String email;
	private String gender;
	@Column (unique = true)
	private long mobile;

}
