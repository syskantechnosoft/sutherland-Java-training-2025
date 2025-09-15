package com.example;

public class RunnableDemo {

	public static void main(String[] args) throws InterruptedException {
		Runnable r1 = new SpellCheckThread();
		Runnable r2 = new GrammerCheckThread();
		
		Thread t1 = new Thread(r1, "Specll-Check");
		Thread t2 = new Thread(r2, "Grammer-Check");
		
		System.out.println("T1 Name:"+t1.getName());
		System.out.println("T2 Name:"+t2.getName());
		
		System.out.println("T1 State:"+t1.getState());
		System.out.println("T2 State:"+t2.getState());
		
		t1.start();
		t2.start();
		
		t2.sleep(1000);
		
		System.out.println("T1 State:"+t1.getState());
		t1.sleep(1000);
		System.out.println("T2 State:"+t2.getState());
	}
}

class SpellCheckThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread is Running now!!!!");
		System.out.println(Thread.currentThread().getName() + " - Spell Check Thread is running");
	}
	
}

class GrammerCheckThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread is Running now!!!!");
		System.out.println(Thread.currentThread().getName() + " - Grammer Check Thread");
	}
	
}

