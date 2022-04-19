package com.javashitang.sort_v2;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    public void testQuickSort() {
        Solution solution = new Solution();
        int[] array = new int[] {5, 4, 3, 2, 1};
        solution.quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}