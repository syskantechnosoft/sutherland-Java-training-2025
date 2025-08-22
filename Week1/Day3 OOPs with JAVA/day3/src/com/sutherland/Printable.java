package com.sutherland;

public interface Printable {
	
	void display();
	
	void show1();
	
	static void print() {
		System.out.println("This is printing");
	}
	
	default void printing() {
		System.out.println("This is printed");
	}

}
