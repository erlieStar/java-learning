package com.javashitang.question_2;

public class Solution {

    public int[] merge(int[] a,int [] b) {
        int[] result = new int[a.length + b.length];
        int indexOfr = 0;
        int indexOfa = 0;
        int indexOfb = 0;
        while (indexOfa < a.length && indexOfb < b.length) {
            if (a[indexOfa] < b[indexOfb]) {
                result[indexOfr++] = a[indexOfa++];
            } else {
                result[indexOfr++] = b[indexOfb++];
            }
        }
        if (indexOfa == a.length) {
            for (int i = indexOfb; i < b.length; i++) {
                result[indexOfr++] = b[i];
            }
        }
        if (indexOfb == b.length) {
            for (int i = indexOfa; i < a.length; i++) {
                result[indexOfr++] = a[i];
            }
        }
        return result;
    }
}
