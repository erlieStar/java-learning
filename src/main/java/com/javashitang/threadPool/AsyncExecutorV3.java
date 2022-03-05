package com.javashitang.threadPool;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author lilimin
 * @since 2022-03-03
 */
public class AsyncExecutorV3 implements Executor {

    private BlockingQueue<Runnable> workQueue;

    private List<WorkThread> workThreadList = new ArrayList<>();

    private RejectedExecutionHandler handler;

    public AsyncExecutorV3(int corePoolSize,
                           BlockingQueue<Runnable> workQueue,
                           RejectedExecutionHandler handler) {
        this.workQueue = workQueue;
        this.handler = handler;
        for (int i = 0; i < corePoolSize; i++) {
            WorkThread workThread = new WorkThread();
            workThread.start();
            workThreadList.add(workThread);
        }
    }

    @SneakyThrows
    @Override
    public void execute(Runnable r) {
        if (!workQueue.offer(r)) {
            handler.rejectedExecution(r);
        }
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
