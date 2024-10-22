package com.basic.runner.executorSerivce.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UserFilter {

    public static List<String> filterUsersByPhoneNumber(List<List<String>> users) throws InterruptedException, ExecutionException {
        int mid = users.size() / 2;

        // Create a thread pool with 2 threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Define two tasks, each handling half of the list
        Callable<List<String>> task1 = () -> filterUsers(users.subList(0, mid));
        Callable<List<String>> task2 = () -> filterUsers(users.subList(mid, users.size()));

        // Submit tasks to ExecutorService
        Future<List<String>> future1 = executorService.submit(task1);
        Future<List<String>> future2 = executorService.submit(task2);

        // Get results from both tasks
        List<String> result1 = future1.get();
        List<String> result2 = future2.get();

        // Shutdown the executor
        executorService.shutdown();

        // Combine the results
        List<String> finalResult = new ArrayList<>();
        finalResult.addAll(result1);
        finalResult.addAll(result2);

        return finalResult;
    }

    // Method to filter users whose phone number starts with +91
    private static List<String> filterUsers(List<List<String>> users) {
        List<String> filteredUsers = new ArrayList<>();
        for (List<String> user : users) {
            String name = user.get(0);
            String phoneNumber = user.get(1);
            if (phoneNumber.startsWith("+91")) {
                filteredUsers.add(name);
            }
        }
        return filteredUsers;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Sample user data: [name, phoneNumber]
        List<List<String>> users = Arrays.asList(
                Arrays.asList("John", "+911234567890"),
                Arrays.asList("Alice", "+441234567890"),
                Arrays.asList("Bob", "+918765432109"),
                Arrays.asList("Eve", "+911112223334"),
                Arrays.asList("Charlie", "+331234567890")
        );

        List<String> filteredUsers = filterUsersByPhoneNumber(users);
        System.out.println("Filtered users: " + filteredUsers);
    }
}
