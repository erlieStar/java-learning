package com.javashitang.myLock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author lilimin
 * @since 2022-02-15
 */
public class MyLockV1Test {

    public static int count = 0;

    @Test
    public void test1() {
        //新建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        //Java8 lambda表达式执行runnable接口
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    count++;
                }
            });
        }
        //关闭线程池
        service.shutdown();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count = " + count);
    }


    @Test
    public void test2() {
        MyLockV1 myLockV1 = new MyLockV1();
        //新建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        //Java8 lambda表达式执行runnable接口
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                myLockV1.lock();
                for (int j = 0; j < 1000; j++) {
                    count++;
                }
                myLockV1.unLock();
            });
        }
        //关闭线程池
        service.shutdown();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count = " + count);
    }

    MyLockV1 myLockV1 = new MyLockV1();

    @Test
    public void test3() {
        method1();
    }

    public void method1() {
        myLockV1.lock();
        System.out.println("method1 lock");
        method2();
        myLockV1.unLock();
    }

    public void method2() {
        myLockV1.lock();
        System.out.println("method2 lock");
        myLockV1.unLock();
    }
}