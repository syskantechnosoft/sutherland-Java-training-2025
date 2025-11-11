package com.example.sparkdemo.service;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class SparkWordCountService {
//	@Autowired
	private final SparkSession sparkSession;

	public SparkWordCountService(SparkSession sparkSession) {
		super();
		this.sparkSession = sparkSession;
	}

	public List<String> getWordCount(List<String> input){
//		JavaRDD<String> rdd = JavaSparkContext.fromSparkContext(sparkSession.sparkContext() ).parallelize(input, 2);
//		return null;
		JavaRDD<String> rdd = JavaSparkContext.fromSparkContext(
				sparkSession.sparkContext()
				).parallelize(input, 2);
		
		return rdd.flatMap(line-> Arrays.asList(line.split(" ")).iterator())
				.map(String::toLowerCase)
				.map(word->word.replaceAll("[^a-z]", ""))
				.filter(word->!word.isEmpty())
				.distinct()
				.collect();
	}
}
