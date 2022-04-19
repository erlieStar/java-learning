package com.javashitang.sort;

public class QuickSort {

    public static void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = sort(a, left, right);
        quickSort(a, left, index - 1);
        quickSort(a, index + 1, right);
    }

    public static int sort(int[] a, int left, int right) {
        int key = a[left];
        while (left < right) {
            // 从high所指位置向前搜索找到第一个关键字小于key的记录和key互相交换
            while (left < right && a[right] >= key) {
                right--;
            }
            a[left] = a[right];
            // 从low所指位置向后搜索，找到第一个关键字大于key的记录和key互相交换
            while (left < right && a[left] <= key) {
                left++;
            }
            a[right] = a[left];
        }
        // 放key值，此时left和right相同
        a[left] = key;
        return left;
    }
}
