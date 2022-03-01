package com.javashitang.interrupt;

import org.junit.Test;

import java.io.IOException;

/**
 * @author lilimin
 * @since 2022-02-21
 */
public class InterruptDemo {


    @Test
    public void test1() throws IOException {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();

        new Thread(() -> {
            thread1.interrupt();
        }).start();
    }

    @Test
    public void test2() {

    }
}
