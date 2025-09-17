package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Employee emp = new Employee(100, "Test","test@gmail.com");
		
		System.out.println(emp);
		
		FileOutputStream fos = new FileOutputStream("d:\\emp_object.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(emp);
		
		System.out.println("Emp object is serialized!!!");
		
	}

}
