package com.example.sparkfafkademo.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class SparkStreamingService implements Serializable {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    private JavaStreamingContext jssc;

    @PostConstruct
    public void startSparkStreaming() {
        // Run Spark Streaming in a separate thread to avoid blocking Spring Boot
        new Thread(() -> {
            try {
                SparkConf conf = new SparkConf()
                        .setAppName("SpringBootSparkStreaming")
                        .setMaster("local[*]"); // Use local cores

                // Initialize JavaStreamingContext with a 5-second batch interval
                jssc = new JavaStreamingContext(conf, Durations.seconds(5));

                Map<String, Object> kafkaParams = new HashMap<>();
                kafkaParams.put("bootstrap.servers", bootstrapServers);
                kafkaParams.put("key.deserializer", StringDeserializer.class);
                kafkaParams.put("value.deserializer", StringDeserializer.class);
                kafkaParams.put("group.id", "spark-streaming-group");
                kafkaParams.put("auto.offset.reset", "latest");
                kafkaParams.put("enable.auto.commit", false);

                Collection<String> topics = Arrays.asList(topicName);

                // Create the DStream
                JavaInputDStream<ConsumerRecord<String, String>> stream =
                        KafkaUtils.createDirectStream(
                                jssc,
                                LocationStrategies.PreferConsistent(),
                                ConsumerStrategies.Subscribe(topics, kafkaParams)
                        );

                // --- PROCESSING LOGIC ---
                // Simple transformation: Print received messages and count words
                stream.map(record -> record.value())
                      .print(); // Print raw messages to console

                // Example: Simple Word Count logic
                stream.map(ConsumerRecord::value)
                      .flatMap(x -> Arrays.asList(x.split(" ")).iterator())
                      .mapToPair(s -> new scala.Tuple2<>(s, 1))
                      .reduceByKey(Integer::sum)
                      .print(); 
                // ------------------------

                jssc.start();
                jssc.awaitTermination();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Spark Streaming Interrupted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @PreDestroy
    public void stopSparkStreaming() {
        if (jssc != null) {
            jssc.stop(true, true);
        }
    }
}
