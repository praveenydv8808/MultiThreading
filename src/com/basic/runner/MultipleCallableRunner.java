package com.basic.runner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultipleCallableRunner {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		List<CallableTask> asList = Arrays.asList(new CallableTask("Nirbhay"), new CallableTask("Abhay"),
				new CallableTask("Aryan"), new CallableTask("Lucky"));
		
		List<Future<String>> futureStringList = executorService.invokeAll(asList);
		for(Future<String> str:futureStringList) {
			System.out.println(str.get());
		}
		
		executorService.shutdown();
	}

}
