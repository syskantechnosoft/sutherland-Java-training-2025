package com.example.sparketl.service;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import org.apache.spark.sql.functions;
import com.example.sparketl.model.Booking;
import com.example.sparketl.model.Flight;

@Service
public class AirlineEtlService {

    private final SparkSession spark;

    public AirlineEtlService(SparkSession spark) {
        this.spark = spark;
    }

    public String runEtlPipeline() {
        // ==========================================
        // 1. EXTRACT (Simulating Data Ingestion)
        // ==========================================
        
        List<Flight> flightData = List.of(
            new Flight("FL001", "NY", "London", 500.0),
            new Flight("FL002", "London", "Paris", 100.0),
            new Flight("FL003", "NY", "Tokyo", 1200.0)
        );

        List<Booking> bookingData = List.of(
            new Booking("B001", "FL001", "John Doe", "ECONOMY"),
            new Booking("B002", "FL001", "Jane Smith", "BUSINESS"),
            new Booking("B003", "FL001", "Bob Brown", "ECONOMY"),
            new Booking("B004", "FL002", "Alice White", "ECONOMY"),
            new Booking("B005", "FL003", "Charlie Green", "BUSINESS")
        );

        // Create Datasets
        Dataset<Row> flightsDf = spark.createDataFrame(flightData, Flight.class);
        Dataset<Row> bookingsDf = spark.createDataFrame(bookingData, Booking.class);

        // ==========================================
        // 2. TRANSFORM
        // ==========================================

        // Transformation Logic:
        // 1. Join Bookings with Flights
        // 2. Apply dynamic pricing: If Class is BUSINESS, price is 2x basePrice
        // 3. Aggregate: Count passengers and Sum revenue per Flight
        
        System.out.println("Bookings DataFrame schema:");
        bookingsDf.printSchema();
        System.out.println("Bookings DataFrame column list: " + java.util.Arrays.asList(bookingsDf.columns()));
        System.out.println("Bookings DataFrame count: " + bookingsDf.count());

        System.out.println("Flights DataFrame schema:");
        flightsDf.printSchema();
        System.out.println("Flights DataFrame column list: " + java.util.Arrays.asList(flightsDf.columns()));
        System.out.println("Flights DataFrame count: " + flightsDf.count());
        Dataset<Row> rawJoined = bookingsDf.join(flightsDf, "flightId");

        Dataset<Row> enrichedDf = rawJoined.withColumn("finalPrice", 
            functions.when(functions.col("seatClass").equalTo("BUSINESS"), functions.col("basePrice").multiply(2))
                     .otherwise(functions.col("basePrice"))
        );

        Dataset<Row> flightStats = enrichedDf.groupBy("flightId", "origin", "destination")
            .agg(
                functions.count("bookingId").as("totalPassengers"),
                functions.sum("finalPrice").as("totalRevenue"),
                functions.collect_list("seatClass").as("classDistribution")
            )
            .orderBy(functions.col("totalRevenue").desc());

        // Show results in console for debugging
        System.out.println("--- ETL Transformation Result ---");
        flightStats.show();

        // ==========================================
        // 3. LOAD
        // ==========================================
        
        // Write to local JSON file (In production: S3, HDFS, or JDBC)
        String outputPath = "output/flight_analytics_" + System.currentTimeMillis();
        
        flightStats.coalesce(1) // Merge into 1 file for local testing
            .write()
            .mode("overwrite")
            .json(outputPath);

        return "ETL Pipeline executed successfully. Output saved to: " + outputPath;
    }
}

