package com.example.day28hbndemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_airline")
public class Airline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String url;
	private long mobile;
	
	

	public Airline(String name, String url, long mobile) {
		super();
		this.name = name;
		this.url = url;
		this.mobile = mobile;
	}

	public Airline(int id, String name, String url, long mobile) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.mobile = mobile;
	}

	public Airline() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Airline [id=" + id + ", name=" + name + ", url=" + url + ", mobile=" + mobile + "]";
	}

	
}
