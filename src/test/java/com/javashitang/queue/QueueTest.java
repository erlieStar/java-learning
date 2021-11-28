package com.javashitang.queue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lilimin
 * @since 2021-11-25
 */
public class QueueTest {


    @Test
    public void test1() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        while (!queue.isEmpty()) {
            System.out.println(queue.take());
        }
    }
}
