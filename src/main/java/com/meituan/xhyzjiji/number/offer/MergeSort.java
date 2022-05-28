package com.meituan.xhyzjiji.number.offer;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        sort(array, left, mid);
        sort(array, mid+1, right);
        merge(array, left, mid, right);
    }

    // 归并，就是在并的时候排序
    public static void merge(int[] array, int left, int mid, int right) {
        int lptr = left, rptr = mid+1;
        // 这里可以用queue代替，如果要反向可以用stack
        int[] temp = new int[right - left + 1];
        int ti = 0;
        while (true) {
            if (lptr <= mid && rptr <= right) {
                if (array[lptr] > array[rptr]) {
                    temp[ti++] = array[rptr];
                    rptr++;
                } else {
                    temp[ti++] = array[lptr];
                    lptr++;
                }
            } else if (lptr > mid) {
                while (rptr <= right) {
                    temp[ti++] = array[rptr++];
                }
                break;
            } else if (rptr > right) {
                while (lptr <= mid) {
                    temp[ti++] = array[lptr++];
                }
                break;
            } else {
                break;
            }
        }
        for (int i = 0; i < temp.length; ++i) {
            array[left + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] tc = new int[]{5,4,3,2,1};
        sort(tc);
        System.out.println("sort ans = " + Arrays.toString(tc));
    }

}
