package com.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 6, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

		System.out.println("Starting main thread " + Thread.currentThread().getName());

		// Submitting 100 Tasks to the Executor

		for (int i = 0; i < 100; i++) {
			int finalI = i;
			executor.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(finalI + " Starting Tasks  " + Thread.currentThread().getName());
					try {
						System.out.println("Inside Try block!!!");
						Thread.sleep(40);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					System.out.println(finalI + " Ending Tasks " + Thread.currentThread().getName());
				}
			}
			);
			
			new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					System.out.println("Thread rejected... Retrying..");
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					executor.submit(r); // retry
				}
			};

			
		}

		System.out.println("Ending Main Thread/Task " + Thread.currentThread().getName());
	}
}
