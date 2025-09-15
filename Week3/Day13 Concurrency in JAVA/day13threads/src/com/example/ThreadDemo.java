package com.example;

import java.util.Set;

public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t1 = new Thread();
		Thread t2 = new Thread("MyThread");
		
		System.out.println("Thread Properties");
		System.out.println("T1 ID:"+t1.getId());
		System.out.println("T2 ID:"+t2.getId());
		System.out.println("T1 Name:"+t1.getName());
		System.out.println("T2 Name:"+t2.getName());
		t2.setPriority(8);
		System.out.println("T1 Priority:"+t1.getPriority());
		System.out.println("T2 Priority:"+t2.getPriority());
		System.out.println("Min Priority:" + Thread.MIN_PRIORITY);
		System.out.println("Norm Priority:" + Thread.NORM_PRIORITY);
		System.out.println("Max Priority:" + Thread.MAX_PRIORITY);
		
		System.out.println("T1 State :"+t1.getState());
		System.out.println("T2 State :"+t2.getState());
		
		// Get all active threads
        Set<Thread> threads = Thread.getAllStackTraces().keySet();

        System.out.println("Currently running threads:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-20s \t %-10s \t %-8s \t %s%n", "Name", "State", "Priority", "Type");
        System.out.println("---------------------------------------------------------");

        // Iterate and display information for each thread
        for (Thread t : threads) {
            String name = t.getName();
            Thread.State state = t.getState();
            int priority = t.getPriority();
            String type = t.isDaemon() ? "Daemon" : "Normal";
            System.out.printf("%-20s \t %-10s \t %-8d \t %s%n", name, state, priority, type);
        }
		
		t1.start();
		System.out.println("T1 State :"+t1.getState());
		System.out.println("T2 State :"+t2.getState());
		
		t2.start();
		System.out.println("T1 State :"+t1.getState());
		System.out.println("T2 State :"+t2.getState());
		
		System.out.println("Is T1 Alive :" + t1.isAlive());
		System.out.println("Is T2 Alive :" + t2.isAlive());
		
		System.out.println("Is T1 Daemon :" + t1.isDaemon());
		System.out.println("Is T2 Daemon :" + t2.isDaemon());
		
		System.out.println("Is T1 Virtual :" + t1.isVirtual());
		System.out.println("Is T2 Virtual :" + t2.isVirtual());
		
		t1.sleep(1000);
		t2.sleep(1000);
		
//		t1.stop();
//		t2.stop();
		
		System.out.println("T1 State after sleep  :"+t1.getState());
		System.out.println("T2 State after sleep:"+t2.getState());
		
		
		System.out.println("Is T1 Alive :" + t1.isAlive());
		System.out.println("Is T2 Alive :" + t2.isAlive());
		
		
		 
	}

}
