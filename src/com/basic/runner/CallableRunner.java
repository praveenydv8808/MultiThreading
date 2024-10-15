package com.basic.runner;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableTask implements Callable<String>{
	
	private String name;

	public CallableTask(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.print("\nTask "+name+" started");
		Thread.sleep(1000);
		return "\nHello "+ name;
	}
	
}


public class CallableRunner {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		Future<String> submit = executorService.submit(new CallableTask("Praveen"));
		System.out.println("new CallableTask(\"Praveen\") executed");
		Future<String> submit1 = executorService.submit(new CallableTask("Hariom"));
		System.out.println("new CallableTask(\"Hariom\") executed");
		Future<String> submit2 = executorService.submit(new CallableTask("nasamama"));
		System.out.println("new CallableTask(\"nasamama\") executed");
		
		String string = submit.get();
		String string2 = submit1.get();
		String string3 = submit2.get();
		
		System.out.println(string);
		System.out.println(string2);
		System.out.println(string3);
		
		System.out.println("Main completed");
		
		executorService.shutdown();
	}

}
