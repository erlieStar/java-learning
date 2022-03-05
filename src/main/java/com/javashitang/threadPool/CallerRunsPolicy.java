package com.javashitang.threadPool;

/**
 * @author lilimin
 * @since 2022-03-03
 */
public class CallerRunsPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r) {
        r.run();
    }
}
