package com.javashitang.lru;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lilimin
 * @since 2022-03-07
 */
public class DoubleListTest {

    @Test
    public void addLast() {
        DoubleList doubleList = new DoubleList();
        doubleList.addLast(new ListNode(1, 1));
        doubleList.addLast(new ListNode(2, 2));
        doubleList.addLast(new ListNode(3, 3));
        doubleList.addLast(new ListNode(4, 4));
        System.out.println(doubleList);
    }

}