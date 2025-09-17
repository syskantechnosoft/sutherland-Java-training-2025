package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OutputStreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			
		InputStream ios = new FileInputStream("d:\\input.txt");

		byte[] data = ios.readAllBytes();
		OutputStream os = new FileOutputStream("d:\\output.txt");
		
		os.write(data);
		
		System.out.println("File contents are copied!!!");
	}

}
