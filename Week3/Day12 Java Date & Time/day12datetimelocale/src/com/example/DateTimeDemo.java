package com.example;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println("today is :" + today);
		
		Date myDate = new Date(125, 8, 12, 14,45);
		System.out.println("Mydate is :" + myDate);
		
		Time currentTime = new Time(System.currentTimeMillis());
		System.out.println("Current Time is :" + currentTime);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
		System.out.println(sdf.format(today));
		
		
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		System.out.println("SQL Date is :" + sqlDate);
		
	}

}
