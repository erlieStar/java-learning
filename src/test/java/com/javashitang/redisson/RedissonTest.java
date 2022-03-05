package com.javashitang.redisson;

import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lilimin
 * @since 2022-03-05
 */
public class RedissonTest {

    private RedissonClient client;

    @Before
    public void before() {
        Config config = new Config();
        //config.setCodec(new org.redisson.client.codec.StringCodec());
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        client = Redisson.create(config);
    }


    @Test
    public void test1() {
        RLock lock = client.getLock("testLock");
        try {
            lock.lock();
            lock.lock();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    @Test
    public void test2() throws IOException, InterruptedException {
        RLock lock = client.getLock("lock");
        new Thread(() -> {
            lock.lock();
            System.out.println("get Lock");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("un Lock");
        }).start();
        TimeUnit.SECONDS.sleep(1);
        if (lock.tryLock()) {
            System.out.println("获锁成功");
        } else {
            System.out.println("获锁失败");
        }
        System.in.read();
    }

    @Test
    public void test3() throws IOException {
        RLock lock = client.getLock("testLock");
        new Thread(() -> {
            lock.lock();
            System.out.println("thread1 lock  " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("thread1 unlock" + System.currentTimeMillis());
        }).start();
        new Thread(() -> {
            lock.lock();
            System.out.println("thread2 lock  " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("thread2 unlock" + System.currentTimeMillis());
        }).start();
        System.in.read();
    }
}
