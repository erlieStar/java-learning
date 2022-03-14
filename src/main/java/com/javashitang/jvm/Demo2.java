package com.javashitang.jvm;

/**
 * @author lilimin
 * @since 2021-07-23
 */
public class Demo2 {

    private static final int _1MB = 1024 * 1024;

    // -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }
}
