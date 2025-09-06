package com.example.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
	  /**
     * Converts a date string to java.sql.Date in yyyy-MM-dd format.
     * @param dateStr The date string (e.g. "06-09-2025" or "06/09/2025").
     * @param inputPattern The pattern of the input string (e.g. "dd-MM-yyyy", "dd/MM/yyyy").
     * @return java.sql.Date in yyyy-MM-dd format, or null if parsing fails.
     */
    public static Date convertStringToSqlDate(String dateStr, String inputPattern) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
            LocalDate localDate = LocalDate.parse(dateStr, inputFormatter);
            return Date.valueOf(localDate); // returns java.sql.Date in yyyy-MM-dd format
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + dateStr);
            return null;
        }
    }

    // Example usage
    public static void main(String[] args) {
        String dateStr = "06-09-2025";
        Date sqlDate = convertStringToSqlDate(dateStr, "dd-MM-yyyy");
        System.out.println("SQL Date: " + sqlDate); // Output: SQL Date: 2025-09-06
    }
}
