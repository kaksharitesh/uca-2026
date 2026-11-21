package com.chitkara.multithreading.readerwriter;

public class Writer extends Thread {
    private final SharedDatabase sharedBuffer;

    public Writer(SharedDatabase sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    public void run() {
        while(!Thread.interrupted()) {
            try {
                sharedBuffer.write(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("Thread "+ Thread.currentThread().getName() + " is interruped");
                return;
            }
        }

    }
}
