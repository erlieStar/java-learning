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
        node.pre = tail.pre;
        tail.pre = node;
        node.pre.next = node;
        node.next = tail;
    }

    public ListNode removeFirst() {
        if (head.next == tail) {
            return null;
        }
        ListNode first = head.next;
        remove(first);
        return first;
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
