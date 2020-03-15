package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] a) {
        int[] aTemp = new int[a.length];
        subSort(a, aTemp, 0, a.length-1);
    }

    private static void subSort(int[] a, int[] aTemp, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = (high + low) / 2;
        subSort(a, aTemp, low, mid);
        subSort(a, aTemp, mid+1, high);
        merge(a, aTemp, low, mid, high);
    }

    // merge(a[low...mid], a[mid+1, high])
    private static void merge(int[] a, int[] aTemp, int low, int mid, int high) {
        if (a[mid] <= a[mid+1]) {
            return;
        }

        int len = high - low + 1;
        for (int lIndex = low, rIndex = mid+1, i = low; i <= high; i++) {
            if (lIndex <= mid && rIndex <= high) {
                aTemp[i] = a[lIndex] < a[rIndex] ? a[lIndex++] : a[rIndex++];
            } else if (lIndex <= mid) {
                aTemp[i] = a[lIndex++];
            } else if (rIndex <= high) {
                aTemp[i] = a[rIndex++];
            }
        }
        System.arraycopy(aTemp, low, a, low, len);
    }

    public static void main(String[] args) {
        int[] a = new int[] {7,8,6,5,4,3,2,1};
        MergeSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
