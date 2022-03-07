package com.javashitang.lru;

/**
 * @author lilimin
 * @since 2021-01-18
 */
public class DoubleList {

    ListNode head;
    ListNode tail;

    public DoubleList() {
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.pre = head;
    }

    public void remove(ListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addLast(ListNode node) {
        tail.pre.next = node;
        node.next = tail;
        node.pre = tail.pre;
        tail.pre = node;
    }

    public ListNode removeFirst() {
        ListNode removeNode = head.next;
        remove(removeNode);
        return removeNode;
    }

    @Override
    public String toString() {
        ListNode tempHead = head;
        ListNode tempTail = tail;
        String str = "";
        while (tempHead.next != null) {
            tempHead = tempHead.next;
            if (tempHead != tempTail) {
                Object key = tempHead.key;
                Object value = tempHead.value;
                str += "(" + key + " = " + value + ") ";
            }
        }
        return str;
    }
}
