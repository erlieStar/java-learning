package com.javashitang.striped;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author lilimin
 * @since 2022-02-18
 */
public class Striped64Test {


    @Test
    public void test1() {
        AtomicLong sum = new AtomicLong();
        // 1
        System.out.println(sum.incrementAndGet());


        LongAdder sum1 = new LongAdder();
        sum1.increment();
        // 1
        System.out.println(sum1);

        LongAccumulator sum2 = new LongAccumulator((a, b) -> a * b, 1);
        for (int i = 1; i < 5; i++) {
            sum2.accumulate(i);
        }
        // 24
        // 4 * 3 * 2 * 1
        System.out.println(sum2);
    }


    @Test
    public void test2() {

    }
}
