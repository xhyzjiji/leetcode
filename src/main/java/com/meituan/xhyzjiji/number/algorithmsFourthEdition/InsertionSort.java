package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            /*int insertVal = a[i];
            for (int j = 0; j < i; j++) {
                if (a[j] > insertVal) {
                    System.arraycopy(a, j, a, j+1, i - j);
                    a[j] = insertVal;
                    break;
                }
            }*/
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] > a[j+1]) {
                    swap(a, j, j+1);
                } else {
                    break;
                }
            }
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
