package com.producer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final String name;

    public Producer(BlockingQueue<Integer> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        int item = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                item++;
                queue.put(item);
                System.out.println(name + " produced: " + item);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}