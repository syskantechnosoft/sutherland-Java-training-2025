package com.example.sparkkafka.service;

import static org.apache.spark.sql.functions.avg;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.count;
import static org.apache.spark.sql.functions.from_json;
import static org.apache.spark.sql.functions.sum;
import static org.apache.spark.sql.functions.to_timestamp;
import static org.apache.spark.sql.functions.window;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class SparkStreamingService {
    
    private static final Logger logger = LoggerFactory.getLogger(SparkStreamingService.class);
    
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    
    @Value("${kafka.topic.input}")
    private String inputTopic;
    
    @Value("${kafka.topic.output}")
    private String outputTopic;
    
    private SparkSession sparkSession;
    private StreamingQuery streamingQuery;
    
    @PostConstruct
    public void init() {
        logger.info("Initializing Spark Streaming Service");
        
        // Create Spark Session
        sparkSession = SparkSession.builder()
                .appName("KafkaSparkStreamingApp")
                .master("local[*]")
                .config("spark.sql.streaming.checkpointLocation", "./checkpoint")
                .getOrCreate();
        
        // Set log level
        sparkSession.sparkContext().setLogLevel("WARN");
        
        // Start streaming in a separate thread
        new Thread(this::startStreaming).start();
    }
    
    private void startStreaming() {
        try {
            logger.info("Starting Kafka-Spark streaming from topic: {}", inputTopic);
            
            // Read from Kafka
            Dataset<Row> kafkaStream = sparkSession
                    .readStream()
                    .format("kafka")
                    .option("kafka.bootstrap.servers", bootstrapServers)
                    .option("subscribe", inputTopic)
                    .option("startingOffsets", "latest")
                    .load();
            
         // 1. Create a list of StructField objects
            List<StructField> fields = new ArrayList<>();
            
         // Add individual StructFields to the list
            fields.add(DataTypes.createStructField("userId", DataTypes.StringType, false)); // field "id" of type Integer, not nullable
            fields.add(DataTypes.createStructField("eventType", DataTypes.StringType, true)); // field "name" of type String, nullable
            fields.add(DataTypes.createStructField("eventvalue", DataTypes.StringType, true)); // field "age" of type Integer, nullable
            fields.add(DataTypes.createStructField("timestamp", DataTypes.StringType, true)); // field "age" of type Integer, nullable

            // Parse JSON from Kafka value
            Dataset<Row> parsedStream = kafkaStream
                    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
                    .select(
                        from_json(col("value"), 
                            DataTypes.createStructType(fields)
                        ).alias("data")
                    )
                    .select("data.userId", "data.eventType", "data.eventvalue", "data.timestamp");
            
            // Convert timestamp to proper type and perform aggregations
            Dataset<Row> timestampedStream = parsedStream
                    .withColumn("eventTime", to_timestamp(col("timestamp")));
            
            Dataset<Row> aggregatedStream = timestampedStream
                    .withWatermark("eventTime", "10 seconds")
                    .groupBy(
                        window(col("eventTime"), "30 seconds", "10 seconds"),
                        col("userId"),
                        col("eventType")
                    )
                    .agg(
                        count("*").alias("eventCount"),
                        sum("eventValue").alias("totalValue"),
                        avg("eventValue").alias("avgValue")
                    )
                    .select(
                        col("window.start").alias("windowStart"),
                        col("window.end").alias("windowEnd"),
                        col("userId"),
                        col("eventType"),
                        col("eventCount"),
                        col("totalValue"),
                        col("avgValue")
                    );
            
            // Write to console for debugging
            streamingQuery = aggregatedStream
                    .writeStream()
                    .outputMode("update")
                    .format("console")
                    .option("truncate", false)
                    .start();
            
            // Optionally write back to Kafka
            /*
            Dataset<Row> outputStream = aggregatedStream
                    .selectExpr("userId as key", "to_json(struct(*)) as value");
            
            streamingQuery = outputStream
                    .writeStream()
                    .format("kafka")
                    .option("kafka.bootstrap.servers", bootstrapServers)
                    .option("topic", outputTopic)
                    .option("checkpointLocation", "./checkpoint")
                    .start();
            */
            
            logger.info("Spark streaming query started successfully");
            streamingQuery.awaitTermination();
            
        } catch (StreamingQueryException e) {
            logger.error("Error in Spark streaming: ", e);
        } catch (Exception e) {
            logger.error("Unexpected error in Spark streaming: ", e);
        }
    }
    
    @PreDestroy
    public void cleanup() {
        logger.info("Stopping Spark Streaming Service");
        if (streamingQuery != null && streamingQuery.isActive()) {
            try {
				streamingQuery.stop();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (sparkSession != null) {
            sparkSession.stop();
        }
    }
}

