package com.meituan.xhyzjiji.number.offer;

import java.util.Arrays;

// 把数组看做一个完全二叉树
public class HeapSort {

    public static void sort(int[] array) {
        constructHeap(array);
        sort(array, array.length);
    }

    // 构造大堆，父节点大于子节点
    public static void constructHeap(int[] array) {
        for (int i = array.length / 2; i >= 1; --i) {
            sink(array, i, array.length);
        }
    }
    // 跟节点与最后一个元素置换，并不再移动，然后重新构造大堆
    public static void sort(int[] array, int len) {
        for (int i = len; i > 1; --i) {
            int temp = array[0];
            array[0] = array[i - 1];
            array[i - 1] = temp;
            sink(array, 1, i - 1);
        }
    }

    public static void sink(int[] array, int pos, int len) {
        int cpos = pos * 2;
        while (cpos <= len) { // 还有子节点
            int left = array[cpos - 1];
            int right = array[cpos];
            int temp = array[pos - 1];

            // 超过len的元素不予置换
            int exPos = (left >= right ? cpos - 1 : cpos);
            if (exPos > len - 1) {
                exPos = len - 1;
            }
            if (temp < array[exPos]) {
                array[pos - 1] = array[exPos];
                array[exPos] = temp;
            } else {
                break;
            }

            pos = cpos;
            cpos = pos * 2;
        }
    }

    public static void main(String[] args) {
        int[] tc = new int[]{7,2,3,4,8,9,6,5,1};
        sort(tc);
        System.out.println(Arrays.toString(tc));
    }

}
