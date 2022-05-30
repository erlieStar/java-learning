package com.javashitang.mulThread;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author lilimin
 * @Date 2022/5/19
 */
public class MyTest {

    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            source.add(i);
        }
        List<List<Integer>> partition = Lists.partition(source, 2);
        for (List<Integer> list : partition) {
            System.out.println(list);
        }
    }

    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            source.add(i);
        }
        List<List<Integer>> partition = Lists.partition(source, 7);
        List<Future> futures = Lists.newArrayList();
        for (List<Integer> list : partition) {
            Future future = executorService.submit(() -> {
                for (Integer integer : list) {
                    System.out.println(integer);
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            futures.add(future);
        }
        for (Future future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void test3() {
        String ids = "1,2,3";
        Set<Long> result = Arrays.stream(ids.split(",")).map(item -> Long.valueOf(item)).collect(Collectors.toSet());
        System.out.println(result);
    }


}
