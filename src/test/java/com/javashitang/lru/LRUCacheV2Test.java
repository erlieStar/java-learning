package com.javashitang.lru;

import org.junit.Test;

/**
 * @author lilimin
 * @since 2021-01-17
 */
public class LRUCacheV2Test {

    @Test
    public void removeEldestEntry() {

        LRUCacheV2<String, String> lruCache = new LRUCacheV2(3);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        lruCache.put("4", "4");
        System.out.println(lruCache);
    }
}