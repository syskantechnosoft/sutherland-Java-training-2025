package com.example;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("d:\\input.txt");
		int d = 0;
		do {
			d = fr.read();
			if (d != -1)
				System.out.print((char) d);
		} while (d != -1);

	}

}
