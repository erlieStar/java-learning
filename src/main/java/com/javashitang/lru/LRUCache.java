package com.javashitang.lru;


import java.util.HashMap;
import java.util.Map;

/**
 * @author lilimin
 * @since 2021-01-17
 */
public class LRUCache<K, V> {

    private int capacity;
    private DoubleList doubleList;
    private Map<K, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        doubleList = new DoubleList();
    }

    public V get(K key) {
        ListNode<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        // 先删除该节点，再接到尾部
        doubleList.remove(node);
        doubleList.addLast(node);
        return node.value;
    }

    public void put(K key, V value) {
        // 直接调用这边的get方法，如果存在，它会在get内部被移动到尾巴，不用再移动一遍,直接修改值即可
        if ((get(key)) != null) {
            map.get(key).value = value;
            return;
        }

        // 如果超出容量，把头去掉
        if (map.size() >= capacity) {
            ListNode listNode = doubleList.removeFirst();
            map.remove(listNode.key);
        }

        // 若不存在，new一个出来
        ListNode node = new ListNode(key, value);
        map.put(key, node);
        doubleList.addLast(node);
    }

    @Override
    public String toString() {
        return doubleList.toString();
    }
}
