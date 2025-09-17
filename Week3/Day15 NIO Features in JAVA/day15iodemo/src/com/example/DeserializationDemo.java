package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationDemo {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("d:\\emp_object.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		System.out.println((Employee) ois.readObject());
		System.out.println("Successfully deserialised Employee Object and reconstructed it using serialised file");
	}

}
