package com.javashitang.wait;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author lilimin
 * @since 2022-03-01
 */
public class WaitDemo {


    @Test
    public void test1() throws InterruptedException {
        long start = System.currentTimeMillis();
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println(System.currentTimeMillis() - start);
    }
}
