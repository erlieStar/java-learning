package com.javashitang.threadPool;

import org.junit.Test;

import java.util.concurrent.*;


public class UseTest {

    @Test
    public void test1() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Runnable r = () -> System.out.println(1 / 0);
        service.execute(r);
        service.shutdown();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> f1 = executor.submit(()-> getPriceByS1());
        Future<Integer> f2 = executor.submit(()-> getPriceByS2());

        Integer price1 = f1.get();
        save(price1);

        Integer price2 = f2.get();
        save(price2);
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> f1 = executor.submit(()-> getPriceByS1());
        Future<Integer> f2 = executor.submit(()-> getPriceByS2());

        BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();
        bq.put(f1.get());
        bq.put(f2.get());

        for (int i = 0; i < bq.size(); i++) {
            Integer price = bq.take();
            save(price);
        }
    }

    @Test
    public void test4() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);

        cs.submit(() -> getPriceByS1());
        cs.submit(() -> getPriceByS2());

        for (int i = 0; i < 2; i++) {
            Integer price = cs.take().get();
            save(price);
        }
    }

    private void save(Integer price1) {
        return;
    }

    private Integer getPriceByS2() {
        return 0;
    }

    private Integer getPriceByS1() {
        return 0;
    }
}
