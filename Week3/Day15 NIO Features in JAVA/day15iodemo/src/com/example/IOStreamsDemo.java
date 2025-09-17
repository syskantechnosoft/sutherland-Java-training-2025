package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOStreamsDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		InputStream ios = new FileInputStream("d:\\input.txt");

		byte[] data = ios.readAllBytes();
		System.out.println("File contents");
		for (byte b : data)
			System.out.print((char) b+" ");

	}

}
