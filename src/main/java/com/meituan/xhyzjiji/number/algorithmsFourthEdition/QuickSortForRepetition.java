package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.util.Arrays;
import org.apache.commons.lang3.tuple.Pair;

public class QuickSortForRepetition {

    public static void sort(int[] a) {
        subSort(a, 0, a.length-1);
    }

    private static void subSort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }

        // partition方法也可以在这里展开
        int[] partition = partition(a, low, high);
        subSort(a, low-1, partition[0]);
        subSort(a, partition[1], high+1);
    }

    private static int[] partition(int[] a, int low, int high) {
        int lt = low;
        int gt = high;
        int eq = low+1;
        int ak = a[low];

        while(eq <= gt) {
            if (a[eq] < ak) {
                swap(a, eq, lt);
                lt++;
            } else if (a[eq] > ak) {
                swap(a, eq, gt);
                gt--;
            } else {
                eq++;
            }
        }

        return new int[]{lt-1, gt+1};
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
