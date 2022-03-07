package com.javashitang.lfu;

import org.junit.Test;

/**
 * @author lilimin
 * @since 2021-01-24
 */
public class LFUCacheTest {

    @Test
    public void get() {
        LFUCache<String, String> lfuCache = new LFUCache(2);
        lfuCache.put("1", "1");
        lfuCache.put("2", "2");
        // 1
        System.out.println(lfuCache.get("1"));
        lfuCache.put("3", "3");
        // 1的频率为2，2和3的频率为1，但2更早之前被访问，所以被清除
        // 结果为null
        System.out.println(lfuCache.get("2"));
    }
}