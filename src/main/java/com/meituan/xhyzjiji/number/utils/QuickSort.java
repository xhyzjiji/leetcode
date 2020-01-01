package com.meituan.xhyzjiji.number.utils;

import com.meituan.xhyzjiji.number.std.Draw;
import com.meituan.xhyzjiji.number.std.DrawListener;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickSort {

    private static final Logger log = LoggerFactory.getLogger(QuickSort.class);

//    private static final Draw draw;
    private static final double interval = 0.05;
    private static volatile boolean waiting = true;
//    static {
//        draw = new Draw();
//        draw.setXscale(0, 1);
//        draw.setYscale(0, 1);
////        draw.setPenRadius(0.001);
//    }

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
//            drawArr(arr);
        }
        swap(arr, pointer, left);
//        drawArr(arr);
        return left;
    }

    private static void swap(int[] arr, int sw1, int sw2) {
        int temp = arr[sw1];
        arr[sw1] = arr[sw2];
        arr[sw2] = temp;
    }

    // 假设arr中元素均大于0
//    private static void drawArr(int[] arr) {
//        int max = -1;
//        for (int a : arr) {
//            max = Math.max(max, a);
//        }
//
//        draw.clear();
//        double x = interval;
//        double y = 0;
//        int N = arr.length;
//        for (int i=0; i < N; i++) {
//            int a = arr[i];
////            draw.line(x, 0, x, (double)a/max/*归一化*/);
//            draw.filledRectangle(1.0*i/N + 0.5/N, a/2.0/max, 0.3/N, a/2.0/max);
//            x += interval;
//        }
//        draw.addListener(new DrawListener() {
//            @Override
//            public void mousePressed(
//                double x,
//                double y
//            ) {
//
//            }
//            @Override
//            public void mouseDragged(
//                double x,
//                double y
//            ) {
//
//            }
//            @Override
//            public void mouseReleased(
//                double x,
//                double y
//            ) {
//
//            }
//            @Override
//            public void mouseClicked(
//                double x,
//                double y
//            ) {
//                waiting = false;
//            }
//            @Override
//            public void keyTyped(char c) {
//
//            }
//            @Override
//            public void keyPressed(int keycode) {
//
//            }
//            @Override
//            public void keyReleased(int keycode) {
//
//            }
//        });
//        while (waiting) {
//            draw.pause(500);
//        }
//        waiting = true;
//    }

    public static void main(String[] args) {
        int[] tc = new int[] {4, 5, 2, 7, 1, 6, 3};
        quickSort(tc, 0, tc.length-1);
        log.info("{}",
                 Arrays.toString(tc));
    }

}
