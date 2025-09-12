package com.example;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class GetDateFromUser {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
        System.out.print("Enter Date of Birth in DD-MM-YYYY format: ");
        String date = input.next();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date utilDate = sdf.parse(date);

        // Show raw Date object
        System.out.println("Parsed Date (Java default): " + utilDate);

        // Format as yyyy/MM/dd
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Formatted Date: " + outputFormat.format(utilDate));

        input.close();
	}

}
