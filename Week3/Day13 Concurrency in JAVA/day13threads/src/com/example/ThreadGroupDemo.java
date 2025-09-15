package com.example;

public class ThreadGroupDemo {
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		MyThread t3 = new MyThread();
		
		
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Anonymous Runnable Class- Run method!!!");
			}
		};
		
		Thread t4 = new Thread(r1, "Runnable Thread");

		// starting the thread
		t1.start();
		t2.start();
		r1.run();
		t3.start();
		t4.start();

		// getting the group of the threads/
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

		// getting the total active count of the threads
		int threadCount = threadGroup.activeCount();

		Thread threadList[] = new Thread[threadCount];
		// enumerating over the thread list
		threadGroup.enumerate(threadList);

		System.out.println("Active threads are:");

		// iterating over the for loop to get the names of
		// all the active threads.
		for (int i = 0; i < threadCount; i++) {
			System.out.println(threadList[i].getName());
			System.out.println(threadList[i].getState());
		}
	}
}

class MyThread extends Thread {
	public void run() {
		System.out.println(Thread.currentThread().getName() + " - is running!!!");
	}
}
