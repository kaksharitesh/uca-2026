package com.chitkara.multithreading.producerconsumer;

public class Consumer extends Thread {
    private final SharedList sharedBuffer;

    public Consumer(SharedList sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    public void run() {
        while(!Thread.interrupted()) {
            try {
                sharedBuffer.consume();
            } catch (InterruptedException e) {
                System.out.println("Thread "+ Thread.currentThread().getName() + " is interrupted");
                return;
            }
        }

    }
}
