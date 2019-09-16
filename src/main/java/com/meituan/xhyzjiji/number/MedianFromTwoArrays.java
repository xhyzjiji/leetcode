package com.meituan.xhyzjiji.number;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedianFromTwoArrays {

    private static final Logger log = LoggerFactory.getLogger(MedianFromTwoArrays.class);

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     *
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * 示例 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * 则中位数是 2.0
     * 示例 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * 则中位数是 (2 + 3)/2 = 2.5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        MedianFromTwoArrays solution = new MedianFromTwoArrays();

        int[] nums1 = new int[] {1, 3};
        int[] nums2 = new int[] {2};
        log.info("{}, {}, median = {}",
                 Arrays.toString(nums1), Arrays.toString(nums2), solution.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[] {1, 2};
        nums2 = new int[] {3, 4};
        log.info("{}, {}, median = {}",
                 Arrays.toString(nums1), Arrays.toString(nums2), solution.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

    }

    // 6 ms, 50.9 MB, O(m+n)不满足题目要求
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;

        int index1 = 0;
        int index2 = 0;

        boolean evenCount = ((l1 + l2) & 0x01) == 0 ? true : false;
        int counter = 0;
        int endCounter = 0;
        if (evenCount) {
            endCounter = (l1 + l2) / 2 - 1;
        } else {
            endCounter = (l1 + l2) / 2;
        }

        while (counter < endCounter) {
            if (index1 < l1 && index2 < l2) {
                if (nums1[index1] < nums2[index2]) {
                    index1++;
                } else {
                    index2++;
                }
            } else if (index1 < l1) {
                index1++;
            } else if (index2 < l2) {
                index2++;
            }
            counter++;
        }

        if (evenCount) {
            int sum = 0;
            for (int i = 0; i < 2; i++) {
                if (index1 < l1 && index2 < l2) {
                    if (nums1[index1] < nums2[index2]) {
                        sum += nums1[index1];
                        index1++;
                    } else {
                        sum += nums2[index2];
                        index2++;
                    }
                } else if (index1 < l1) {
                    sum += nums1[index1];
                    index1++;
                } else if (index2 < l2) {
                    sum += nums2[index2];
                    index2++;
                }
            }
            return (double)sum / 2;
        } else {
            if (index1 < l1 && index2 < l2) {
                if (nums1[index1] < nums2[index2]) {
                    return nums1[index1];
                } else {
                    return nums2[index2];
                }
            } else if (index1 < l1) {
                return nums1[index1];
            } else if (index2 < l2) {
                return nums2[index2];
            }
        }

        return 0;
    }

}
