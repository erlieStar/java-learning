package com.javashitang.reactor;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lilimin
 * @since 2022-01-26
 */
public class RandomUtil {

    private static final Random random = new Random();

    @SneakyThrows
    public static int doBusiness() {
        int level = random.nextInt(1000);
        int time;
        if (level <= 900) {
            time = 1;
        } else if (level <= 950) {
            time = 10;
        } else if (level <= 990) {
            time = 100;
        } else {
            time = 1000;
        }
        TimeUnit.MILLISECONDS.sleep(time);
        return time;
    }
}
