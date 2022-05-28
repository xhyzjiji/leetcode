package com.meituan.xhyzjiji.number.offer;

import java.util.Arrays;

// 选择排序
public class SelectionSort {

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] tc = new int[]{5,4,3,2,1};
        sort(tc);
        System.out.println(Arrays.toString(tc));
    }

}
