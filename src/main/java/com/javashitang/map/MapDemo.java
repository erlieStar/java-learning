package com.javashitang.map;

import java.util.TreeMap;

/**
 * @author lilimin
 * @since 2021-11-05
 */
public class MapDemo {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 100);
        map.put(2, 200);
        map.put(3, 300);
        System.out.println(map.lastKey());
        System.out.println(map.firstKey());
    }
}
