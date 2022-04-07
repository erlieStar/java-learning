package com.javashitang.question_1;

public class Solution {

    public int[] getArray(int[] a) {
        int indexOfR = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i - 1]) {
                a[indexOfR++] = a[i];
            }
        }
        return a;
    }
}
