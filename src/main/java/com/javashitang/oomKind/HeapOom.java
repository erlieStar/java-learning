package com.javashitang.oomKind;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author lilimin
 * @since 2022-03-15
 */
public class HeapOom {

    private static final int _1MB = 1024 * 1024;

    // -Xms20m -Xmx20m
    public static void main(String[] args) {
        List<byte[]> list = Lists.newArrayList();
        while (true) {
            list.add(new byte[_1MB]);
        }
    }
}
