package com.javashitang.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author lilimin
 * @since 2021-11-04
 */
public class TimerDemo {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("test");
            }
        }, 1000);
        TimeUnit.SECONDS.sleep(10);
    }
}
