package com.javashitang.oomKind;

import java.nio.ByteBuffer;

/**
 * @author lilimin
 * @since 2022-03-15
 */
public class DirectMemoryOom {

    private static final int _1MB = 1024 * 1024;

    // -XX:MaxDirectMemorySize=2m
    public static void main(String[] args) {
        ByteBuffer.allocateDirect(3 * _1MB);
    }
}
