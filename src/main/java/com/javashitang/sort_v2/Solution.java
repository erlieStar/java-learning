package com.javashitang.sort_v2;

public class Solution {

    public void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = sort(array, left, right);
        quickSort(array, left, index);
        quickSort(array, index + 1, right);
    }

    public int sort(int[] array, int left, int right) {
        int key = array[left];
        while (left < right) {
            while (left < right && array[right] > key) {
                right--;
            }
            array[left] = array[right];
            while (left < right && array[left] < key) {
                left++;
            }
            array[left] = array[right];
        }
        array[left] = key;
        return left;
    }
}
