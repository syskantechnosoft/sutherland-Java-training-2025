package com.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StreamsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List numberList = Arrays.asList(1,7,5,9,21,48,6,17);
		System.out.println(numberList);
		Collections.sort(numberList);
		System.out.println(numberList);
		
		//Streams helps to do multiple operations in a single line. 
		
		//Using enhanced for loop
		for (Object i:numberList)
			System.out.println(i);
		
		//Using Streams
		numberList.stream().forEach(n->System.out.println(n));
		

	}

}
