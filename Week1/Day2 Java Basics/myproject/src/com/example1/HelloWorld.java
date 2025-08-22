package com.example1;  //package declaration line

import java.util.Date;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date today = new Date();
		
		//Fully Qualified Name of the class
		java.sql.Date yesterday = new java.sql.Date(System.currentTimeMillis());

		System.out.println("today is :" +  today);
		System.out.println("Yesterday was :" +  yesterday);
	}

}
