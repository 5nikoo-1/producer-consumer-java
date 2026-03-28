package com.producer;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Shared queue with max capacity of 5
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

        // Thread pool: 2 producers, 3 consumers
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(new Producer(queue, "Producer-1"));
        executor.submit(new Producer(queue, "Producer-2"));
        executor.submit(new Consumer(queue, "Consumer-1"));
        executor.submit(new Consumer(queue, "Consumer-2"));
        executor.submit(new Consumer(queue, "Consumer-3"));

        // Run for 5 seconds then shut down gracefully
        Thread.sleep(5000);
        executor.shutdownNow();

        if (executor.awaitTermination(2, TimeUnit.SECONDS)) {
            System.out.println("All threads stopped cleanly.");
        } else {
            System.out.println("Timeout: some threads still running.");
        }
    }
}