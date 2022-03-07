package com.javashitang.lru;


import java.util.HashMap;
import java.util.Map;

/**
 * @author lilimin
 * @since 2021-01-17
 */
public class LRUCache {

    int capacity;
    DoubleList doubleList;
    Map<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        doubleList = new DoubleList();
    }

    public int get(int key) {
        ListNode listNode = map.get(key);
        if (listNode == null) {
            return -1;
        }
        this.makeRecent(listNode);
        return listNode.value;
    }

    public void put(int key, int value) {
        ListNode listNode = map.get(key);

        if (listNode != null) {
            this.makeRecent(listNode);
            listNode.value = value;
            return;
        }

        if (map.size() == capacity) {
            removeOld();
        }

        addRecent(key, value);

    }

    public void makeRecent(ListNode listNode) {
        doubleList.remove(listNode);
        doubleList.addLast(listNode);
    }


    public void addRecent(int key, int value) {
        ListNode listNode = new ListNode(key, value);
        doubleList.addLast(listNode);
        map.put(key, listNode);
    }

    public void removeOld() {
        ListNode listNode = doubleList.removeFirst();
        map.remove(listNode.key);
    }

    @Override
    public String toString() {
        return doubleList.toString();
    }
}
