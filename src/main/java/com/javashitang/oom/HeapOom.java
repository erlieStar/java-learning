package com.javashitang.oom;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author lilimin
 * @since 2022-03-15
 */
public class HeapOom {

    private static final int NUM = 1024;

    // -Xms20m -Xmx20m
    public static void main(String[] args) {
        List<byte[]> list = Lists.newArrayList();
        for (int i = 0; i < NUM; i++) {
            list.add(new byte[NUM * NUM]);
        }
    }
}
