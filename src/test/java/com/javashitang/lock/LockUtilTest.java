package com.javashitang.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LockUtilTest {


    @Test
    public void testLock() {

        String lockKey = "Lockkey";
        String lockValue = String.valueOf(System.currentTimeMillis());

        new Thread(() -> {
            if (LockUtil.lock(lockKey, lockValue, 10000)) {
                System.out.println("1获取锁");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("1释放锁");
                    LockUtil.unlock(lockKey, lockValue);
                }
            } else {
                System.out.println("1没有拿到锁");
            }
        }).start();


        new Thread(() -> {
            if (LockUtil.lock(lockKey, lockValue, 3000)) {
                System.out.println("2获取锁");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("2释放锁");
                    LockUtil.unlock(lockKey, lockValue);
                }
            } else {
                System.out.println("2没有拿到锁");
            }
        }).start();


        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}