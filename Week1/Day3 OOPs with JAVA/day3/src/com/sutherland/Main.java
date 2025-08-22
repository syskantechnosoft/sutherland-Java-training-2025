package com.sutherland;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyInterface myInterface = new MyInterface() {
			
			@Override
			public void show() {
				// TODO Auto-generated method stub
				System.out.println("This is anonymous implementation!!!");
			}
		};
		
		//Lambda or anonymous method or inline-method 
		MyInterface myInterface1 =  ()->System.out.println("This is lambda implementation!!!");
		
		myInterface.show();
		myInterface1.show();
	}

}
