package com.example;

public class ExceptionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		int b = 0;
		try {
			System.out.println(a/b);
		} catch (ArithmeticException ex) {
			ex.printStackTrace();
		}
		System.out.println("Program Ends here!!!");

	}

}
