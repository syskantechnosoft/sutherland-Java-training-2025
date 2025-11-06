package com.example.sparkdemo;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;
import scala.Tuple2;

import java.util.Arrays;

public class WordCount {
	public static void main(String[] args) {

		// Step 1: Create a Spark configuration and context
		SparkConf conf = new SparkConf().setAppName("WordCountExample").setMaster("local[*]"); 
		// run locally using all CPU cores

		JavaSparkContext sc = new JavaSparkContext(conf);

		// Step 2: Load the input file
		JavaRDD<String> lines = sc.textFile("d:/data/sample.txt");

		// Step 3: Split lines into words
		JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
		System.out.println("words :" + words);

		// Step 4: Map each word to a (word, 1) pair
		JavaPairRDD<String, Integer> wordPairs = words.mapToPair(word -> new Tuple2<>(word, 1));
		System.out.println("word pairs:" + wordPairs);

		// Step 5: Reduce by key (sum up counts for each word)
		JavaPairRDD<String, Integer> wordCounts = wordPairs.reduceByKey(Integer::sum);

		// Step 6: Print results
		wordCounts.foreach(result -> System.out.println(result._1() + " : " + result._2()));

		// Step 7: Stop the Spark context
		sc.stop();
	}
}
