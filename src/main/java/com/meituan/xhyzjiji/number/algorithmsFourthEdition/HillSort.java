package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.util.Arrays;

public class HillSort {

    public static void sort(int[] a, int factor) {
        int N = a.length;
        int h = 1;
        factor = factor < 2 ? 2 : factor;
        int hUpper = N / factor;
        hUpper = hUpper < 1 ? 1 : hUpper;
        hUpper = hUpper > N/2 ? N/2 : hUpper;
        while (true) {
            int htemp = factor * h;
            if (htemp < hUpper) {
                h = htemp;
            } else {
                break;
            }
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i-h; j >= 0; j -= h) { // a[i]为插入元素
                    if (a[j] > a[j+h]) { // 子数组内比较
                        swap(a, j, j+h);
                    } else {
                        break;
                    }
                }
            }

            h /= factor;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[] {7,8,6,5,4,3,2,1};
        HillSort.sort(a, 2);
        System.out.println(Arrays.toString(a));
    }

}
