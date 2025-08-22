package com.sutherland;

public class Starter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a =10;
		User userObject = new User();
		System.out.println("user obj :" + userObject);
		System.out.println(userObject.hashCode());
		//Address of Memory location
		//userObject is a memory reference. It's a pointer
		
		User user1 = new User();
		User user2 = new User(100, "abc", null, "abc@gmail.com" );
		User user3;
		User user4 = user1;
		user1.setId(101);
		user1.setName("xyz");
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user4);
		System.out.println(user1.hashCode());
		System.out.println(user2.hashCode());
	//	System.out.println(user3.hashCode());
		System.out.println(user4.hashCode());
		
		//Anonymous Inner class
		Employee emp = new Employee() {
			
			@Override
			public void show() {
				// TODO Auto-generated method stub
				
			}
		};
		emp.display();
		
	}

}
