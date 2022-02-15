package com.javashitang.myLock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lilimin
 * @since 2022-02-15
 */
public class MyLockV1 {

    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        Thread current = Thread.currentThread();
        waiters.add(current);

        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
        }

        waiters.remove();
    }

    public void unLock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}
