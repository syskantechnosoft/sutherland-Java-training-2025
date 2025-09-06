package com.example;

public abstract class LambdaDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		display();
		
//		()->System.out.println("I'm Lambda");
		
		//Anonymous Inner class implementation
		MyInterface myInterface = new MyInterface() {
			
			@Override
			public void display() {
				// TODO Auto-generated method stub
				System.out.println("Anonymous Inner class implementation");
			}
		};
		
		myInterface.display();
		
		
		//Lambda Implementation 
		MyInterface myInterface2 = ()->System.out.println("I'm Lambda Implementation");
		myInterface2.display();
		
		//Method Reference		
		MyInterface myInterface3 = myInterface2::display;  
		myInterface3.display();
		
	}
	
	//Normal Method (Method signature + Method body)
	public static void display()
	{  //method body starts here
		System.out.println("i'm display method");
	} //method body ends here
	
	//abstract method or non-concrete or in-complete method -- Method Signature only
	public abstract void show();

}
