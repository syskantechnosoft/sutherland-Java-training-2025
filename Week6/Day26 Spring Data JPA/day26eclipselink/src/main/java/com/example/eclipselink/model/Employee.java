package com.example.eclipselink.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	private BigInteger mobile;

	private String name;

	public Employee() {
	}

	public Employee(int id, String email, BigInteger mobile, String name) {
		super();
		this.id = id;
		this.email = email;
		this.mobile = mobile;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigInteger getMobile() {
		return this.mobile;
	}

	public void setMobile(BigInteger mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", mobile=" + mobile + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, mobile, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(mobile, other.mobile)
				&& Objects.equals(name, other.name);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}