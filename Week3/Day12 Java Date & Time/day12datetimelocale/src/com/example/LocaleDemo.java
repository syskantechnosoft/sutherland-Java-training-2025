package com.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Locale.Builder;

@SuppressWarnings(value = "deprecation")
public class LocaleDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println(today);

		// Creating Locale object using Deprecated Parameterized constructor
		Locale PST = new Locale("en", "US");

		System.out.println("PST :" + PST);

		// Creating Locale Object using Builder Pattern
		Locale US = new Builder().setLanguage("en").setRegion("US").build();

		System.out.println("US Locale :" + US);

		LocalDate localDate = LocalDate.of(2025, 9, 12);
		System.out.println("Local Date is :" + localDate);
		
		LocalDate now  = LocalDate.now();
		System.out.println("now :" + now);
		
		System.out.println(ZoneId.getAvailableZoneIds());
//		ZoneId zoneId = ZoneId.getAvailableZoneIds
		LocalDate CET = LocalDate.now(ZoneId.of("CET"));
		
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("CET"));
		System.out.println("CET :" + ldt);

	}

}
