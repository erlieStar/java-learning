package com.javashitang.thread;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Solution {

    public static void main(String[] args) throws IOException, InterruptedException {
        Queue<WorkThread> queue = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            WorkThread workThread = new WorkThread();
            workThread.start();
            queue.add(workThread);
        }
        for (int i = 1; i <= 100; i++) {
            WorkThread thread = queue.poll();
            thread.addNum(i);
            queue.add(thread);
        }
        System.in.read();
    }

    public static class WorkThread extends Thread {

        private BlockingQueue<Integer> queue = new LinkedBlockingQueue(100);

        private int num;

        public void addNum(int num) {
            queue.add(num);
        }

        @Override
        public void run() {
            Integer num = null;
            while (true) {
                try {
                    num = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + num);
            }
        }
    }
}
