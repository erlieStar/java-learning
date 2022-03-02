package com.javashitang.concurrent.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lilimin
 * @since 2021-03-03
 */
public class CyclicBarrierUseCase3 {

    public static void main(String[] args) {
        CyclicBarrier barrier =
                new CyclicBarrier(3, () -> {
                    System.out.println("凑够人了");
                    throw new RuntimeException();
                });
        ExecutorService service = Executors.newCachedThreadPool();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int num = i;
            service.submit(() -> {
                try {
                    System.out.println(num + " 准备去棋牌馆");
                    TimeUnit.SECONDS.sleep(random.nextInt(5));
                    System.out.println(num + " 到达");
                    barrier.await();
                    System.out.println(num + " 斗地主");
                } catch (Exception e) {
                    barrier.reset();
                    e.printStackTrace();
                }
            });
        }
    }
}
