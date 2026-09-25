package com.chitkara.trees;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private static List<Integer> balance = new ArrayList<>();
    private String Name;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        for(int j =0;j<1;j++) {
            int workerCount = 10;
            Thread[] worders = new Thread[workerCount];
            for (int i = 0; i < workerCount; i++) {
                worders[i] = new Thread(new Deposit());
            }
            for (int i = 0; i < workerCount; i++) {
                worders[i].start();
            }

            for (int i = 0; i < workerCount; i++) {
                worders[i].join();
            }
            if (balance.size() != 10) {
                System.out.println(j+ ". Race condition happened " + balance + "(" + balance.size() + ")");
            }
            balance = new ArrayList<>();
        }
        System.out.println("time taken = " + (System.currentTimeMillis() - startTime));
    }

    private static class Deposit extends Thread{
        private final Integer mutex = 1;
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (mutex) {
                AccountService.balance.add(1);
                //critical section
            }
        }
    }
    // mutex  lock

}
