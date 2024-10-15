package com.basic.runner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleAnyCallableRunner {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		List<CallableTask> tasks = Arrays.asList(new CallableTask("Nirbhay"), new CallableTask("Abhay"),
				new CallableTask("Aryan"), new CallableTask("Lucky"));
		
		String result = executorService.invokeAny(tasks);
		System.out.println(result);
		executorService.shutdown();
	}
}
