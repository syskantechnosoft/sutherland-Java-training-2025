package com.sutherland;

//POJO Class - Plain Old Java Object  - simple class --- Abstract or non-concrete class
public abstract class Employee {

	private int id;
	private String name;
	
	

	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public Employee() {
		super();
	}


	 void display() {
		System.out.println("id :" + id + " name:" + name);
	}
	
	public abstract void show();  //abstract- method - non-concrete/in-complete method signature 

//	public abstract void show1();
}
