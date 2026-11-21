package com.chitkara.multithreading.readerwriter;

import java.util.concurrent.CountDownLatch;

public class Simulator {

    public static void main(String[] args) throws InterruptedException {
        SharedDatabase sharedBuffer = new SharedDatabase();
        CountDownLatch c1 = new CountDownLatch(5);
        Thread w1 = new Writer(sharedBuffer);
        Thread w2 = new Writer(sharedBuffer);
        Thread r1 = new Reader(sharedBuffer);
        Thread r2 = new Reader(sharedBuffer);
        Thread r3 = new Reader(sharedBuffer);

        w1.setName("w1");
        w2.setName("w2");
        r1.setName("r1");
        r2.setName("r2");
        r3.setName("r3");

        w1.start();
        w2.start();
        r1.start();
        r2.start();
        r3.start();


        Thread.sleep(10000);
        w1.interrupt();
        w2.interrupt();
        r1.interrupt();
        r2.interrupt();
        r3.interrupt();

        c1.await();



        System.out.println("Main thread exiting : final values in shared buffer " + sharedBuffer.buffer);

    }
}
