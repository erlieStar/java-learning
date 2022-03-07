package com.javashitang.lfu;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author lilimin
 * @since 2021-01-18
 */
public class LFUCache {

    Map<Integer, Integer> keyToVal;
    Map<Integer, Integer> keyToFreq;
    Map<Integer, LinkedHashSet<Integer>> freqTokeys;

    // 最小的频次
    int minFreq;
    int capacity;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqTokeys = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        Integer v = keyToVal.get(key);
        if (v == null) {
            return -1;
        }
        // 增加key对应的频率
        increaseFrey(key);
        return v;
    }

    public void put(int key, int value) {
        if (this.capacity <= 0) {
            return;
        }
        if (keyToVal.containsKey(key)) {
            // 重新设置值
            keyToVal.put(key, value);
            increaseFrey(key);
            return;
        }

        // 超出容量，删除频率最低的key
        if (keyToVal.size() == capacity) {
            // 删除访问频率最低的key
            removeMinFreqKey();
        }

        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        freqTokeys.putIfAbsent(1, new LinkedHashSet<>());
        freqTokeys.get(1).add(key);
        this.minFreq = 1;
    }

    // 删除出现频率最低的key
    private void removeMinFreqKey() {
        LinkedHashSet<Integer> keyList = freqTokeys.get(minFreq);
        Integer deleteKey = keyList.iterator().next();
        keyList.remove(deleteKey);
        if (keyList.isEmpty()) {
            // 这里删除元素后不需要重新设置minFreq
            // 因为put方法执行完会将minFreq设置为1
            freqTokeys.remove(keyList);
        }
        keyToVal.remove(deleteKey);
        keyToFreq.remove(deleteKey);
    }

    // 增加频率
    private void increaseFrey(int key) {
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqTokeys.get(freq).remove(key);
        freqTokeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqTokeys.get(freq + 1).add(key);
        if (freqTokeys.get(freq).isEmpty()) {
            freqTokeys.remove(freq);
            // 最小频率的set为空，key被移动到minFreq+1对应的set了
            // 所以minFreq也要加1
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }
}
