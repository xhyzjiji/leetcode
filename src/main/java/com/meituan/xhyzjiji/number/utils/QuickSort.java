package com.meituan.xhyzjiji.number.utils;

import com.meituan.xhyzjiji.number.std.Draw;
import com.meituan.xhyzjiji.number.std.DrawListener;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickSort {

    private static final Logger log = LoggerFactory.getLogger(QuickSort.class);

    public static void quickSort(int[] arr, int left, int right) {
        int pointer = findElementPosition(arr, left, right);
        if (left < pointer-1) {
            quickSort(
                arr,
                left,
                pointer - 1
            );
        }
        if (pointer+1 < right) {
            quickSort(
                arr,
                pointer + 1,
                right
            );
        }
    }

    /**
     * 返回元素正确位置
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int findElementPosition(int[] arr, int left, int right) {
        int pointer = left;
        while (left < right) {
            // 每次循环，left停在比arr[pointer]大的元素，right停在比arr[pointer]小的元素，然后交换
            // 前提：pointer取left时，在最后一次循环中，如果left先走，left累加至right，此时arr[left]和arr[right]都大于arr[pointer]，arr[pointer]与arr[left-1]交换才能保证正确性，但要注意left-1可能越界的问题；
            // 如果right先走，right减少至left，此时arr[left]和arr[right]都小于arr[pointer]，arr[pointer]与arr[left[交换就可以了
            while (left < right && arr[right] >= arr[pointer]) {
                right--;
            }
            while (left < right && arr[left] <= arr[pointer]) {
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr, pointer, left);
        return left;
    }

    private static void swap(int[] arr, int sw1, int sw2) {
        int temp = arr[sw1];
        arr[sw1] = arr[sw2];
        arr[sw2] = temp;
    }

    public static void main(String[] args) {
        int[] tc = new int[] {4, 5, 2, 7, 1, 6, 3};
        quickSort(tc, 0, tc.length-1);
        log.info("{}",
                 Arrays.toString(tc));
    }

}
