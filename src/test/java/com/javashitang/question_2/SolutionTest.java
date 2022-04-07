package com.javashitang.question_2;

import com.javashitang.question_2.Solution;
import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    public void testMerge() {
        Solution solution = new Solution();
        int[] merge = solution.merge(new int[]{1, 3, 4, 9}, new int[]{2, 5, 6});
        for (int i = 0; i < merge.length; i++) {
            System.out.println(merge[i] + " ");
        }
        System.out.println();
    }

    public void testMerge2() {
        Solution solution = new Solution();
        int[] merge = solution.merge(new int[]{1, 2, 3}, new int[]{4, 5, 6});
        for (int i = 0; i < merge.length; i++) {
            System.out.println(merge[i] + " ");
        }
        System.out.println();
    }
}