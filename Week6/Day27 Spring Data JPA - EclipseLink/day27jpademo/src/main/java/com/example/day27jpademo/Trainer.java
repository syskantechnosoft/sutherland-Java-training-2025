package com.example.day27jpademo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the trainer database table.
 * 
 */
@Entity
@NamedQuery(name="Trainer.findAll", query="SELECT t FROM Trainer t")
public class Trainer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="last_name")
	private String lastName;

	public Trainer() {
	}

	
	public Trainer(String email, String firstName, String gender, String lastName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.gender = gender;
		this.lastName = lastName;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Override
	public String toString() {
		return "Trainer [id=" + id + ", email=" + email + ", firstName=" + firstName + ", gender=" + gender
				+ ", lastName=" + lastName + "]";
	}
	
	

}