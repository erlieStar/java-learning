package com.javashitang.lock;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lilimin
 * @since 2022-02-19
 */
public class LockTest {

    @Test
    public void testLock() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        Thread thread1 = new Thread(() -> {
            readLock.lock();
            System.out.println("thread1 read lock " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 read unlock " + System.currentTimeMillis());
            readLock.unlock();
        });
        Thread thread2 = new Thread(() -> {
            readLock.lock();
            System.out.println("thread2 read lock " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2 read unlock " + System.currentTimeMillis());
            readLock.unlock();
        });
        Thread thread3 = new Thread(() -> {
            writeLock.lock();
            System.out.println("thread3 write lock " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread3 write unlock " + System.currentTimeMillis());
            writeLock.unlock();
        });
        thread1.start();
        thread2.start();
        thread3.start();
        System.in.read();
    }


    @Test
    public void test2() {

    }
}
