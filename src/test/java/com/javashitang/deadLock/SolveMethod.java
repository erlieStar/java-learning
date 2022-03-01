package com.javashitang.deadLock;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lilimin
 * @since 2021-03-05
 */
public class SolveMethod {

    private static Object lockA = new Object();
    private static Object lockB = new Object();

    @Test
    public void test1() {

    }

    @Test
    public void test2() {

    }

    @Test
    public void test3() throws IOException {
        Thread threadA = new Thread(() -> {
            synchronized (lockA) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get lockA");
                synchronized (lockB) {
                    System.out.println("threadA run finish");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (lockA) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get lockB");
                synchronized (lockB) {
                    System.out.println("threadB run finish");
                }
            }
        });
        threadA.setName("myThreadA");
        threadB.setName("myThreadB");
        threadA.start();
        threadB.start();
        System.in.read();
    }
}
