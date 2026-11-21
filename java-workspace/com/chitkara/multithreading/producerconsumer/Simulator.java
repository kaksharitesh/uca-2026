package com.chitkara.multithreading.producerconsumer;

public class Simulator {

    public static void main(String[] args) throws InterruptedException {
        SharedList sharedBuffer = new SharedList();
        Thread p1 = new Producer(sharedBuffer);
        Thread p2 = new Producer(sharedBuffer);
        Thread p3 = new Producer(sharedBuffer);
        Thread c1 = new Consumer(sharedBuffer);

        p1.setName("p1");
        p2.setName("p2");
        p3.setName("p3");
        c1.setName("c1");

        p1.start();
        p2.start();
        p3.start();
        c1.start();


        Thread.sleep(10000);
        p1.interrupt();
        p2.interrupt();
        p3.interrupt();
        c1.interrupt();


        p1.join();
        p2.join();
        p3.join();
        c1.join();
        System.out.println("Main thread exiting : final values in shared buffer " + sharedBuffer.sharedBuffer);

    }
}
