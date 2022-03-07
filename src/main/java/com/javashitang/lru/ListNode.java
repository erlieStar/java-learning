package com.javashitang.lru;


/**
 * @author lilimin
 * @since 2021-01-18
 */
public class ListNode {

    int key;
    int value;
    ListNode pre;
    ListNode next;

    public ListNode() {}

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
