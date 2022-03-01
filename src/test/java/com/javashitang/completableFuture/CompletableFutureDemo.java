package com.javashitang.completableFuture;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lilimin
 * @since 2020-08-29
 */
public class CompletableFutureDemo {

    @Test
    public void create() throws Exception {
        CompletableFuture<Integer> intFuture = CompletableFuture.completedFuture(100);
        // 100
        System.out.println(intFuture.get());

        CompletableFuture<Void> voidFuture = CompletableFuture.runAsync(() -> System.out.println("hello"));
        // null
        System.out.println(voidFuture.get());

        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> "hello");
        // hello
        System.out.println(stringFuture.get());
    }

    @Test
    public void whenComplete() throws Exception {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }).whenComplete((v, e) -> {
            // hello
            System.out.println(v);
        });
        // hello
        System.out.println(future.get());
    }


    @Test
    public void thenCombine() throws Exception {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            return "欢迎关注 ";
        }).thenApply(t -> {
            return t + "微信公众号 ";
        }).thenCombine(CompletableFuture.completedFuture("Java识堂"), (t, u) -> {
            return t + u;
        }).whenComplete((t, e) -> {
            // 欢迎关注 微信公众号 Java识堂
            System.out.println(t);
        });
    }

    @Test
    public void applyToEither() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            return "欢迎关注微信公众号";
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            return "Java识堂";
        });
        CompletableFuture future = future1.applyToEither(future2, str -> str);
        // 欢迎关注微信公众号 Java识堂 随机输出
        System.out.println(future.get());
    }

    @Test
    public void allOf() throws InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            return "欢迎关注";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            return "微信公众号";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            return "Java识堂";
        });
        // 欢迎关注 微信公众号 Java识堂
        CompletableFuture.allOf(future1, future2, future3)
                .thenApply(v ->
                        Stream.of(future1, future2, future3)
                                .map(CompletableFuture::join)
                                .collect(Collectors.joining(" ")))
                .thenAccept(System.out::print);
    }

    @Test
    public void anyOf() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            return "欢迎关注";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            return "微信公众号";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            sleepRandom();
            return "Java识堂";
        });
        CompletableFuture<Object> resultFuture = CompletableFuture.anyOf(future1, future2, future3);
        System.out.println(resultFuture.get());
    }

    public void sleepRandom() {
        Random random = new Random();
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(6));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        stringFuture.complete("word");
        // word
        System.out.println(stringFuture.get());
    }

    @Test
    public void test3() throws Exception {
        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> "hello");
        TimeUnit.SECONDS.sleep(1);
        stringFuture.complete("word");
        // hello
        System.out.println(stringFuture.get());
    }

    @Test
    public void test4() throws Exception {
        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        // java.util.concurrent.ExecutionException: java.lang.RuntimeException
        stringFuture.completeExceptionally(new RuntimeException());
        // hello
        System.out.println(stringFuture.get());
    }

    @Test
    public void test5() throws Exception {
        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> "hello");
        TimeUnit.SECONDS.sleep(1);
        stringFuture.completeExceptionally(new RuntimeException());
        // hello
        System.out.println(stringFuture.get());
    }

    @Test
    public void test6() throws Exception {
        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> "hello");
        stringFuture = stringFuture.thenApply((x) -> x + " world").thenApply(String::toUpperCase);
        // HELLO WORLD
        System.out.println(stringFuture.get());
    }

    @Test
    public void test7() throws Exception {
        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> "hello");
        stringFuture = stringFuture.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " world"));
        // hello world
        System.out.println(stringFuture.get());
    }

    @Test
    public void test8() throws Exception {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });
        // from future1 和 from future2 随机输出
        CompletableFuture<Void> future = future1.acceptEither(future2, str -> System.out.println(str));
        future.get();
    }

    @Test
    public void test9() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "test1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "test2");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "test3");

        // test1 test2 test3
        CompletableFuture.allOf(future1, future2, future3).thenApply(v ->
                Stream.of(future1, future2, future3).
                        map(CompletableFuture::join).
                        collect(Collectors.joining(" "))).thenAccept(System.out::print);
    }

    @Test
    public void test11() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "test1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "test2");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "test3");

        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2, future3);
        // test1
        System.out.println(future.get());

    }


    @Test
    public void test12() throws Exception {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });
        CompletableFuture<String> future = future1.applyToEither(future2, str -> str);
        // from future1 和 from future2 随机输出
        System.out.println(future.get());
    }

    @Test
    public void test13() {
        // hello world
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world").thenAccept(System.out::print);
    }

    @Test
    public void test14() {
        // hello world
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world").whenComplete((result, throwable) -> System.out.println(result));
    }

    @Test
    public void test15() {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s + " world";})
                .whenComplete((result, throwable) -> System.out.println(result));
    }

    @Test
    public void thenApply() {
        CompletableFuture.supplyAsync(() -> {
            return "hello ";
        }).thenAccept(str -> {
            // hello world
            System.out.println(str + "world");
        }).thenRun(() -> {
            // task finish
            System.out.println("task finish");
        });
    }

    @Test
    public void exception() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100 / 0;
        }).thenApply(num -> {
            return num + 10;
        }).exceptionally(throwable -> {
            return 0;
        });
        // 0
        System.out.println(future.get());
    }

    @Test
    public void test110() throws Exception {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            String str = null;
            return str.length();
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("正常结果为" + v);
            } else {
                // 发生异常了java.util.concurrent.CompletionException: java.lang.NullPointerException
                System.out.println("发生异常了" + e.toString());
            }
        });
        System.out.println(future.get());
    }


    @Test
    public void completeExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture future = new CompletableFuture();
        future.completeExceptionally(new RuntimeException());
        // 抛出异常 java.util.concurrent.ExecutionException: java.lang.RuntimeException
        Object object = future.get();
    }

    public static void main(String[] args) {
        /**
         * ForkJoinPool.commonPool-worker-1 在路上耗时4秒
         * ForkJoinPool.commonPool-worker-2 在路上耗时3秒
         * ForkJoinPool.commonPool-worker-1 到达车站了
         * ForkJoinPool.commonPool-worker-2 到达车站了
         * 老司机，发车
         */
        Random random = new Random();
        CompletableFuture future1 = CompletableFuture.runAsync(() -> {
            int time = random.nextInt(5);
            System.out.println(Thread.currentThread().getName() + " 在路上耗时" + time + "秒");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 到达车站了");
        });
        CompletableFuture future2 = CompletableFuture.runAsync(() -> {
            int time = random.nextInt(5);
            System.out.println(Thread.currentThread().getName() + " 在路上耗时" + time + "秒");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 到达车站了");
        });
        CompletableFuture.allOf(future1, future2).thenRun(() -> {
            System.out.println("老司机，发车");
        });
    }

    public CompletableFuture<Integer> getResult() {
        CompletableFuture<Integer> result1 = new CompletableFuture();
        CompletableFuture<Integer> result2 = new CompletableFuture();
        new Thread(() -> {
            sleepRandom();
            result1.complete(1);
        }).start();

        new Thread(() -> {
            sleepRandom();
            result2.complete(2);
        }).start();

        return result1.thenCombine(result2, (num1, num2) -> {
            return num1 + num2;
        });
    }

    @Test
    public void test21() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> future = getResult();
        // 线程阻塞在这里等待结果，直到等到结果3
        System.out.println(future.get());
        // 2145
        System.out.println(System.currentTimeMillis() - start);
    }


    @Test
    public void test30() throws ExecutionException, InterruptedException, IOException {
        CompletableFuture<Integer> completableFuture = new CompletableFuture();

        new Thread(() -> {
            try {
                System.out.println("开始取");
                System.out.println(completableFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            System.out.println("设置值");
            completableFuture.complete(10);
        }).start();
        System.in.read();
    }
}
