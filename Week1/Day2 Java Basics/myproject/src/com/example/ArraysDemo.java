package com.example;

public class ArraysDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[5]; //fixed size number array
		int[] b = {8,56,34,27,75,89,31}; // fixed size numbers array
		
		System.out.println(a); //prints the starting memory address
		System.out.println(b);
		
		//primitive declaration style
		String str = "Hello"; // This represents String object with a ref name str.
		//Object style declaration 
		String str1 = new String("Hello World!!!");
	}

}
