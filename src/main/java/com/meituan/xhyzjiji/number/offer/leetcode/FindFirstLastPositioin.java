package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class FindFirstLastPositioin {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = firstSearch(nums, target);
        res[1] = lastSearch(nums, target);
        return res;
    }

    private int firstSearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        if (left > right) {
            return -1;
        }
        do {
            int mid = (right + left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left < right);
        if (right >= 0 && nums[right] == target) {
            return right;
        } else if (right+1 < nums.length && nums[right+1] == target) {
            return right+1;
        } else {
            return -1;
        }
    }

    private int lastSearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        if (left > right) {
            return -1;
        }
        do {
            int mid = (right + left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left < right);
        if (left < nums.length && nums[left] == target) {
            return left;
        } else if (left-1 >= 0 && nums[left-1] == target) {
            return left-1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        FindFirstLastPositioin firstLastPositioin = new FindFirstLastPositioin();
        int[] tc = new int[] {5,7,7,8,8,10};
        System.out.println(Arrays.toString(firstLastPositioin.searchRange(tc, 8)));
    }

}
