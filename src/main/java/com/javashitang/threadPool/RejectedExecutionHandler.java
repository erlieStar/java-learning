package com.javashitang.threadPool;


/**
 * @author lilimin
 * @since 2022-03-03
 */
public interface RejectedExecutionHandler {

    void rejectedExecution(Runnable r);
}
