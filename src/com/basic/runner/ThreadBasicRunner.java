package com.basic.runner;

class Task1 extends Thread{
	@Override
	public void run() {
		System.out.print("\nTask 1 started..");
		for (int i = 101; i <= 199; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\ntask1 Done.. ");
		
	}
}

class Task4 extends Thread{
	@Override
	public void run() {
		for(int i=1;i<5;i++) {
			System.out.println(Thread.currentThread().getName()+" - count - "+i);
//			Thread.yield();
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}

class Task2 implements Runnable{
	@Override
	public void run() {
		System.out.print("\nTask2 Started..");
		for (int i = 201; i <= 299; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\ntask2 Done.. ");
	}
}


public class ThreadBasicRunner {

	public static void main(String[] args) throws InterruptedException {
		
		Task4 task4T1 = new Task4();
		Task4 task4T2 = new Task4();
		
		task4T1.setPriority(10);
		task4T1.start();
		
//		task4T1.join();
		
		task4T2.setPriority(1);
		task4T2.start();
		
		
		System.out.print("\nTask1 kickked off..");
		Task1 task1 = new Task1();
		task1.start();
		task1.setPriority(10); //set the priority of complete the task (10 = High, 1 = Low, 5 = Normal)
		
		System.out.print("\nTask2 kickked off..");
		Task2 task2 = new Task2();
		Thread task2Thread = new Thread(task2);
		task2Thread.start();
		task2Thread.setPriority(1);//set the priority of complete the task (10 = High, 1 = Low, 5 = Normal)
		
		task1.join(); // below code will wait for task1 to complete
		task2Thread.join();
		
		System.out.print("\nTask3 kickked off..");
		for (int i = 301; i <= 399; i++) {
			System.out.print(i + " ");
		}

		System.out.print("\ntask3 Done.. ");

		System.out.print("\nMain Done");
	}

}
