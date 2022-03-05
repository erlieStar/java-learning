package com.javashitang.threadPool;

/**
 * @author lilimin
 * @since 2022-03-03
 */
public class AsyncExecutorV1 implements Executor {

    @Override
    public void execute(Runnable r) {
        new Thread(r).start();
    }
}
