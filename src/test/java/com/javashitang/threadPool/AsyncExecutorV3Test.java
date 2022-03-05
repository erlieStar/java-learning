package com.javashitang.threadPool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author lilimin
 * @since 2022-03-03
 */
public class AsyncExecutorV3Test {

    @Test
    public void execute() throws IOException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(2);
        AsyncExecutorV3 asyncExecutorV3 = new AsyncExecutorV3(2, blockingQueue, new AbortPolicy());
        for (int i = 0; i < 8; i++) {
            asyncExecutorV3.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.in.read();
    }
}