package com.javashitang.jdk;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    @Test
    public void test1() {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("a", "b");
        map.put("a", "b");
    }

    @Test
    public void test2() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
