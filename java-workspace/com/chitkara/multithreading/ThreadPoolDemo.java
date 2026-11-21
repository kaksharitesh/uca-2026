package com.chitkara.multithreading;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> submit = executorService.submit(new Counter());
        Future<?> submit2 = executorService.submit(new Counter());
        Future<?> submit3 = executorService.submit(new Counter());
        System.out.println(submit3.get());
        System.out.println(submit2.get());
        System.out.println(submit.get());

        executorService.shutdown();


    }

    public static class Counter implements Callable<String> {

        public String call() {
            return Thread.currentThread().getName();
        }
    }
}
