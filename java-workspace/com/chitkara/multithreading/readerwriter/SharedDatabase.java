package com.chitkara.multithreading.readerwriter;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedDatabase {
    private final int MAX_SIZE = 10;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition readReady = lock.newCondition();
    private final Condition writeReady = lock.newCondition();
    public String buffer = null;
    CountDownLatch latch = new CountDownLatch(1);
    private int readerCount = 0;
    private int writerCount = 0;

    public void write(String msg) throws InterruptedException {
        String id = Thread.currentThread().getName();
        System.out.println(id + " trying to get lock");

        lock.lock();
        try {
            while (readerCount > 0 || writerCount > 0) {
                System.out.println(id + " is in wait queue");
                writeReady.await();
            }
            System.out.println(id + " out of wait queue");
            writerCount++;
        } finally {
            if (lock.isLocked())
                lock.unlock();
        }

        if (buffer == null) buffer = msg;
        else
            buffer = buffer + "->" + msg;
        latch.countDown();
        try {
            Thread.sleep(100);
            lock.lock();
            writerCount--;
            if (new Random().nextInt() % 2 == 0) {
                System.out.println(id + " sending write notification");
                writeReady.signal();
                System.out.println(id + " sending read notification");
                readReady.signalAll();
            } else {
                System.out.println(id + " sending read notification");
                readReady.signalAll();
                System.out.println(id + " sending write notification");
                writeReady.signal();
            }
        } finally {
            if (lock.isLocked())
                lock.unlock();
        }
    }

    public void read() throws InterruptedException {
        String id = Thread.currentThread().getName();
        latch.await();
        System.out.println(id + " trying to get lock");
        lock.lock();
        try {
            while (writerCount > 0) {
                System.out.println(id + " is in wait queue");
                readReady.await();
            }
            readerCount++;
            System.out.println(id + " out of wait queue reader count = " + readerCount);
        } finally {
            if (lock.isLocked())
                lock.unlock();
        }

        System.out.println(id + " reads msg = " + buffer);
        try {
            Thread.sleep(100);
            lock.lock();
            readerCount--;
            System.out.println(id + " sending write notification reader count = " + readerCount);
            writeReady.signal();
        } finally {
            if (lock.isLocked())
                lock.unlock();
        }

    }

}
