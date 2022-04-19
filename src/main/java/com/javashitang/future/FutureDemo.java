package com.javashitang.future;

import com.javashitang.online.BankDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureDemo {

    public static void main(String[] args) throws Exception {

        FutureTask<Integer> task1 = new FutureTask<>(new Task1());
        FutureTask<Integer> task2 = new FutureTask<>(new Task2());

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        int sum = task1.get() + task2.get();

        System.out.println("sum " + sum);
        System.out.println("cost " + (System.currentTimeMillis() - start));

    }
}

class Task1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return 100;
    }
}

class Task2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return 200;
    }
}
