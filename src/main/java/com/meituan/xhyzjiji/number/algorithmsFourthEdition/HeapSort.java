package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.util.Arrays;

public class HeapSort {

    public static void sort(int[] a, boolean asc) {
        for (int i = a.length-1; i > 0; i--) {
            subSort(a, i+1, asc);
            swap(a, 0, i);
            System.out.println(Arrays.toString(a));
        }
    }

    // 每次排序可以确定最小/最大值
    private static void subSort(int[] a, int len, boolean asc) {
        for (int i = 1; i < len; i++) {
            int c = i;
            int p;
            while (c > 0) {
                p = (c-1)/2;
                if (asc) {
                    if (a[c] > a[p]) {
                        swap(a, c, p);
                        c = p;
                    } else {
                        break;
                    }
                } else {
                    if (a[c] < a[p]) {
                        swap(a, c, p);
                        c = p;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     *
     * @param a
     * @param c
     * @param asc true: 最大堆，false: 最小堆
     */
    private static void swim(int[] a, int c, boolean asc) {
        int p;
        while (c > 0) {
            p = (c-1)/2;
            if (asc) {
                if (a[c] > a[p]) {
                    swap(a, c, p);
                    c = p;
                } else {
                    break;
                }
            } else {
                if (a[c] < a[p]) {
                    swap(a, c, p);
                    c = p;
                }
            }
        }
    }

    private static void sink(int[] a, int len, int p, boolean asc) {
        int cBase = (p << 1) + 1;
        while (cBase < len) {
            // 针对二叉树
            int cMost = a[cBase];
            int cMostIndex = cBase;
            if (asc) {
                for (int i = 1, c = cBase+i; i <= 2 && c < len; i++, c = cBase+i) {
                    if (cMost < a[c]) {
                        cMostIndex = c;
                        cMost = a[c];
                    }
                }

                if (a[p] < cMost) {
                    swap(a, p, cMostIndex);
                } else {
                    break;
                }
            } else {
                for (int i = 1, c = cBase+i; i <= 2 && c < len; i++, c = cBase+i) {
                    if (cMost > a[c]) {
                        cMostIndex = c;
                        cMost = a[c];
                    }
                }

                if (a[p] > cMost) {
                    swap(a, p, cMostIndex);
                } else {
                    break;
                }
            }
        }
    }

    public static void sortImproved(int[] a, boolean asc) {
        for (int i = 1; i < a.length; i++) {
            swim(a, i, asc);
        }
        // 或者这么实现
        /*
        for (int i = a.length/2-1; i >= 0; i--) {
            sink(a, a.length, i, asc);
        }
        */

        swap(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
        for (int i = a.length-1; i > 0; i--) {
            sink(a, i, 0, asc);
            swap(a, 0, i - 1);
            System.out.println(Arrays.toString(a));
        }
    }

    private static void swap(int[] a, int m, int n) {
        int temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }

    public static void main(String[] args) {
        int[] tc = new int[]{2,5,7,8,1,3,6,9,0};
        sortImproved(tc, true);
        System.out.println(Arrays.toString(tc));
    }

}
