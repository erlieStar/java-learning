package com.javashitang.question_1;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    public void testGetArray() {
        Solution solution = new Solution();
        int[] array = solution.getArray(new int[]{1, 1, 2, 2, 2, 3, 4, 5});
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
    }

    public void testGetArray2() {
        Solution solution = new Solution();
        int[] array = solution.getArray(new int[]{1, 2, 3, 4, 5});
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
    }

    public void testGetArray3() {
        Solution solution = new Solution();
        int[] array = solution.getArray(new int[]{1, 1, 2, 2, 5});
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
    }
}