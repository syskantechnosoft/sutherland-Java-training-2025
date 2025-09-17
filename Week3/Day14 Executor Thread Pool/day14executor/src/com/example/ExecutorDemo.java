package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Task implements Callable<String> {

	private String message;

	public Task(String message) {
		this.message = message;
	}

	public String call() throws Exception {
		return "Hi " + message + "!";
	}
}

public class ExecutorDemo {
	public static void main(String[] args) {

		Task task = new Task("First Task");

		// Creating object of ExecutorService class and Future object Class
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		Future<String> result = executorService.submit(task);

		// Try block to check for exceptions
		try {
			System.out.println(result.get());
		}

		// Catch block to handle the exception
		catch (InterruptedException | ExecutionException e) {

			System.out.println("Error occurred while executing the submitted task");
			e.printStackTrace();// Resource consuming operation or costly operation

			System.out.println("Exception :" + e.getMessage());
		}

		// Cleaning resource and shutting down JVM by saving JVM state using shutdown()
		// method
		executorService.shutdown();
	}
}
