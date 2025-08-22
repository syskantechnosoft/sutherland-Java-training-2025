package com.example;

public class VariablesDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a; // Variable declaration
		a = 10; // variable initialization - Assigning a value to variable.
		System.out.println("a=" + a);
		a = 25; // variable re-assigning - updating
		System.out.println("a=" + a);

		final int B = 10; // variable declaration & Initialization
		// B=20;

		// B=25; //Can't re-assign a final variable.

		byte byteData = 75;
		boolean isAlive = true;
		short age = 54;
		int x = 0, y=0, z = 0;
		float average = 34.75f;
		double interest = 5.75;
		long mobile = 7867986745l;

		int binary = 0b1011;
		int octal = 05466457;
		int hexa = 0x6384Afc;

		System.out.println("byteDate =" + byteData);
		System.out.println("isAlive =" + isAlive);
		System.out.println("age =" + age);
		System.out.printf("x=%d y=%d z=%d", x,y,z);
		System.out.println("average =" + average);
		System.out.println("interest =" + interest);
		System.out.println("mobile =" + mobile);
		System.out.println("byteDate =" + byteData);
		System.out.println("binary =" + binary);
		System.out.println("octal =" + octal);
		System.out.println("hexa =" + hexa);
	}

}
