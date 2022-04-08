package com.javashitang.question_4;

public class QuickSort {

    public void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = sort(a, left, right);
        quickSort(a, left, index);
        quickSort(a, index + 1, right);
    }

    public int sort(int[] a,int left, int right) {
        int target = a[left];
        while (left < right) {
            while (left < right && a[right] >= target) {
                right--;
            }
            a[left] = a[right];
            while (left < right && a[left] <= target) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = target;
        return left;
    }
}
