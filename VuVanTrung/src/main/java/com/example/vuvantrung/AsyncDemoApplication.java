package com.example.vuvantrung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableAsync  // Enable async support
public class AsyncDemoApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(AsyncDemoApplication.class, args);
        var asyncService = context.getBean(AsyncService.class);

        // Test without CompletableFuture
        asyncService.testWithoutCompletableFuture();

        // Test with CompletableFuture
        try {
            asyncService.testWithCompletableFuture();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Service
class AsyncService {

    // Simulating a heavy task with a delay
    private String heavyTask(String name) throws InterruptedException {
        Thread.sleep(2000);  // Simulate a 3-second delay
        return "Hello " + name;
    }

    @Async  // Marks the method to run asynchronously
    public CompletableFuture<String> doSomethingWithAsync(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return heavyTask(name);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    // Synchronous method
    public String doSomethingWithoutAsync(String name) {
        try {
            return heavyTask(name);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    // Test method to compare synchronous execution
    public void testWithoutCompletableFuture() {
        long startTime = System.currentTimeMillis();

        // Running tasks synchronously
        String result1 = doSomethingWithoutAsync("D");
        String result2 = doSomethingWithoutAsync("E");
        String result3 = doSomethingWithoutAsync("F");

        // Printing results
        System.out.println("- testWithoutCompletableFuture -");
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed Time: " + elapsedTime + " ms");
    }

    // Test method to compare asynchronous execution using CompletableFuture
    public void testWithCompletableFuture() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        // Running tasks asynchronously
        CompletableFuture<String> resultPromise = doSomethingWithAsync("A");
        CompletableFuture<String> result2Promise = doSomethingWithAsync("B");
        CompletableFuture<String> result3Promise = doSomethingWithAsync("C");

        // Waiting for results and printing them
        System.out.println("- testWithCompletableFuture -");
        String result1 = resultPromise.get();  // Blocking until result is available
        String result2 = result2Promise.get();
        String result3 = result3Promise.get();

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed Time: " + elapsedTime + " ms");
    }
}
