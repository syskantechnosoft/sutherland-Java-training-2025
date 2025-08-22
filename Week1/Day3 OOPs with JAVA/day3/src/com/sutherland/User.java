package com.sutherland;


import java.sql.Date;
//User defined or custom  class

/** 
 * This is Documentation Comment
 */

//Simple Bean Class
public class User {
	//instance variables 
	private int id;
	private String name;
	private Date dob;
	private String email;
	private long mobile;
	
	//Fully parameterized constructor
	public User(int id, String name, Date dob, String email, long mobile) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.mobile = mobile;
	}
	/* Multi -line comment
	 *  span multiple
	 *  lines
	 */

	//Default or no-arg constructor
	public User() {
		super();
	}
	
	//partially parameterized constructor
	public User(int id, String name, Date dob, String email) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		
	}
	
	//methods  -- Accessor & Mutators (getter & Setters)
	
	//setter
	public void setId(int id) {
		this.id=id;
	}
	//getter
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + ", email=" + email + ", mobile=" + mobile + "]";
	}
	
	
}
