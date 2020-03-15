package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] a) {
        subSort(a, 0, a.length-1);
    }

    private static int findPosition(int[] a, int low, int high) {
        // 取头部的好处在于循环体内的比较和交换与a[k]无关，去除a[k]元素，数组还是连续的
        int k = low;
        while (low < high) {
            // 最后一次循环中，可以认为已经满足p位置要求，low会停在比a[k]大的位置，high因为low<high，所以也会停在比a[k]大的位置，
            // 而a[k]需要与比他小的元素交换，这里的实现是不满足要求的
            /*while (low < high && a[low] <= a[k]) {
                low++;
            }
            while (low < high && a[high] >= a[k]) {
                high--;
            }*/

            // 最后一次循环中，数组存在位置p，high会停留在比a[k]小的位置，low因为low<high的条件也会停留在比a[k]小的位置上
            while (low < high && a[high] >= a[k]) {
                high--;
            }
            while (low < high && a[low] <= a[k]) {
                low++;
            }

            swap(a, low, high);
        }
        swap(a, k, low);
        return low;
    }

    private static void subSort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int p = findPosition(a, low, high);
        subSort(a, low, p-1);
        subSort(a, p+1, high);
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] tc = new int[]{5, 9, 5, 5, 8, 3, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 7, 5, 5, 2, 1};
        sort(tc);
        System.out.println(Arrays.toString(tc));
    }

}
