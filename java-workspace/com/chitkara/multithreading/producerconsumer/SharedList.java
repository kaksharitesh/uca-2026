package com.chitkara.multithreading.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class SharedList {
    private final int MAX_SIZE = 10;
    public BlockingQueue<String> sharedBuffer = new LinkedBlockingQueue<>();
    private Object mutex = new Object();

    private boolean isFull() {
        return sharedBuffer.size() == MAX_SIZE;
    }

    public void produce(String msg) throws InterruptedException {
        String id = Thread.currentThread().getName();
        System.out.println(id + " trying to get lock");
        synchronized (mutex) {
            while (isFull()) {
                System.out.println(id + " is on wait");
                mutex.wait();
            }
            sharedBuffer.add(msg); //critical section
            System.out.println(id + " has the lock and added " + msg + " buffer is now " + sharedBuffer);
            Thread.sleep(100);
            if (isFull()) {
                System.out.println(id + " sending notify all");
                mutex.notifyAll();
            }
        }
    }

    public void consume() throws InterruptedException {
        String id = Thread.currentThread().getName();
        System.out.println(id + " trying to get lock");
        synchronized (mutex) {
            while (!isFull()) {
                System.out.println(id + " is on wait");
                mutex.wait();
            }

            System.out.println(id + " has the lock and consuming  buffer  " + sharedBuffer);
            Thread.sleep(100);
            sharedBuffer.clear();
            System.out.println(id + " sending notify all");
            mutex.notifyAll();
        }
    }

}
