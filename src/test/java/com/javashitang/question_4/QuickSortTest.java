package com.javashitang.question_4;

import junit.framework.TestCase;

public class QuickSortTest extends TestCase {

    public void testQuickSort() {
        QuickSort quickSort = new QuickSort();
        int[] a = new int[]{5,4,3};
        quickSort.quickSort(a, 0, a.length-1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
    }
}