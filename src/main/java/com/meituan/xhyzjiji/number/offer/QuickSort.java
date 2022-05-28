package com.meituan.xhyzjiji.number.offer;

import java.util.Arrays;

// 时间复杂度:O(nlogn),空间复杂度O(1)
public class QuickSort {

    public static void sort(int[] array) {
        internalSort(array, 0, array.length - 1);
    }

    // 关键：选择一个数字（一般是array头部），把数字小的移到制定数字的左边，大的移到右边，然后递归处理即可
    private static void internalSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int originStart = start;
        int originEnd = end;
        while (start < end) {
            if (array[end] >= array[start]) {
                end--;
            } else {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
                start++;
            }
        }
        internalSort(array, originStart, start-1);
        internalSort(array, start+1, originEnd);
    }

    public static void main(String[] args) {
        int[] tc = new int[]{5,4,3,2,1,5,6,7,8,9};
        sort(tc);
        System.out.println(Arrays.toString(tc));
    }

}
