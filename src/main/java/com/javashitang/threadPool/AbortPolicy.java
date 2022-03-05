package com.javashitang.threadPool;

/**
 * @author lilimin
 * @since 2022-03-03
 */
public class AbortPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r) {
        throw new RuntimeException("queue is full");
    }
}
