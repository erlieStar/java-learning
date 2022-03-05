package com.javashitang.threadPool;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;

/**
 * @author lilimin
 * @since 2022-03-03
 */
public class AsyncExecutorV2 implements Executor {

    private BlockingQueue<Runnable> workQueue;

    public AsyncExecutorV2(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        WorkThread workThread = new WorkThread();
        workThread.start();
    }

    @SneakyThrows
    @Override
    public void execute(Runnable r) {
        workQueue.add(r);
    }

    class WorkThread extends Thread {

        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }
    }
}
