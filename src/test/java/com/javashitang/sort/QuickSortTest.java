package com.javashitang.sort;

import junit.framework.TestCase;

public class QuickSortTest extends TestCase {

    public void testQuickSort() {
        int[] array = new int[] {5, 4, 5, 2, 5};
        QuickSort.quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
        System.out.println();
    }
}