package com.chitkara.multithreading.producerconsumer;

public class Producer extends Thread {
    private final SharedList sharedBuffer;

    public Producer(SharedList sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    public void run() {
        while(!Thread.interrupted()) {
            try {
                sharedBuffer.produce(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("Thread "+ Thread.currentThread().getName() + " is interruped");
                return;
            }
        }

    }
}
