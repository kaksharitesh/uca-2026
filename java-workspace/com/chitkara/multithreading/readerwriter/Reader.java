package com.chitkara.multithreading.readerwriter;

public class Reader extends Thread {
    private final SharedDatabase sharedBuffer;

    public Reader(SharedDatabase sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    public void run() {
        while(!Thread.interrupted()) {
            try {
                sharedBuffer.read();
            } catch (InterruptedException e) {
                System.out.println("Thread "+ Thread.currentThread().getName() + " is interrupted");
                return;
            }
        }

    }
}
