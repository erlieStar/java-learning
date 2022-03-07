package com.javashitang.lru;

import org.junit.Test;

/**
 * @author lilimin
 * @since 2021-01-17
 */
public class LRUCacheTest {

    @Test
    public void get() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        System.out.println(lruCache);
        lruCache.put(2, 2);
        System.out.println(lruCache);
        lruCache.get(2);
        System.out.println(lruCache);
        lruCache.put(3, 3);
        System.out.println(lruCache);
    }


    @Test
    public void test1() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        System.out.println(lruCache);

        System.out.println(lruCache.get(2));

        System.out.println(lruCache);
        lruCache.put(4, 4);
        lruCache.get(1);
        System.out.println(lruCache.get(3));
        lruCache.get(4);
    }
}