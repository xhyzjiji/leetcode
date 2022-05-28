package com.meituan.xhyzjiji.number.offer.leetcode;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 */
public class Shuzuzhongdenixudui {

    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{5,4,2,1}));
    }

    public static int reversePairs(int[] nums) {
        return sort(nums, 0, nums.length-1);
    }

    /**
     * 只计算子集的逆序数量，不会重复，比如：
     * ( 5 | 4 ) | ( 2 | 1 )
     *   1个逆序     1个逆序
     *  （ 4 5 ) | ( 1 2 )
     *        4个逆序
     * 一共6个逆序对
     */
    public static int sort(int[] nums, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) / 2;
        int c1 = sort(nums, l, mid);
        int c2 = sort(nums, mid+1, r);
        int c3 = merge(nums, l, mid, r);
        return c1+c2+c3;
    }

    public static int merge(int[] nums, int l, int mid, int r) {
        int count = 0;
        int[] temp = new int[r-l+1];
        int lp = l, rp = mid+1, tp = 0;
        while (tp < temp.length) {
            if (lp < mid+1 && rp <= r) {
                if (nums[lp] > nums[rp]) {
                    count += (mid - lp + 1);
                    temp[tp++] = nums[rp++];
                } else {
                    temp[tp++] = nums[lp++];
                }
            } else if (lp < mid+1) {
                temp[tp++] = nums[lp++];
            } else {
                temp[tp++] = nums[rp++];
            }
        }
        tp = 0; lp = l;
        while (tp < temp.length) {
            nums[lp++] = temp[tp++];
        }
        return count;
    }

}
