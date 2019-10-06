package com.meituan.xhyzjiji.number.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindSumTarget {

    private static final Logger log = LoggerFactory.getLogger(FindSumTarget.class);

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        FindSumTarget solution = new FindSumTarget();

        log.info(Arrays.toString(solution.twoSum(new int[]{3, 2, 4}, 6)));
        log.info(Arrays.toString(solution.twoSum2(new int[]{3, 2, 4}, 6)));
        log.info(Arrays.toString(solution.twoSum3(new int[]{3, 2, 4}, 6)));
        log.info(Arrays.toString(solution.twoSum4(new int[]{3, 2, 4}, 6)));
    }

    // leetcode最低用时1ms

    //6 ms, 39.1 MB
    public int[] twoSum4(int[] nums, int target) {
        Map<Integer/*value*/, Integer/*index*/> map = new HashMap<Integer, Integer>((int)(nums.length * 1.34)); // 用nums.length能降到5ms，但只是取巧的方法

        // TODO: 已知答案唯一，所以一个数最多出现两遍，出现3遍的肯定不是答案
        for (int i = 0; i < nums.length; i++) {
            int expected = target - nums[i];
            if (map.containsKey(expected)) {
                return new int[] {map.get(expected), i}; // 如果答案是两个重复数，这里已经得到答案，重复数不需要存入map了
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }

    //39 ms, 37.8 MB
    public int[] twoSum3(int[] nums, int target) {
        int i, j;
        for (i = 0; i < nums.length; i++) {
            int expected = target - nums[i];
            for (j = i + 1; j < nums.length; j++) {
                if (expected == nums[j]) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[0];
    }

    //69 ms,38.1 MB
    public int[] twoSum2(int[] nums, int target) {
        int i, j;
        for (i = 0; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[0];
    }

    //106 ms,37.9 MB
    public int[] twoSum(int[] nums, int target) {
        // quick sort
        int[] originIndex = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            originIndex[i] = i;
        }
        quickSort(nums, originIndex, 0, nums.length - 1);
        log.info("sorted nums = {}", Arrays.toString(nums));

        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        while (leftIndex < rightIndex) {
            int left = nums[leftIndex];
            int right = nums[rightIndex];

            int sum = left + right;
            if (sum == target) {
                int[] ans = new int[] {originIndex[leftIndex], originIndex[rightIndex]};
                Arrays.sort(ans);
                return ans;
            } else if (sum > target) {
                rightIndex--;
            } else if (sum < target) {
                leftIndex++;
            }
        }
        return new int[0];
    }

    // 计算base值得正确位置，并返回
    public int partition(int[] nums, int[] originIndex, int left, int right) {
        int baseIndex = left;
        int base = nums[baseIndex];
        while (left < right) {
            // TODO: 为什么要先从右边开始？这两个while循环反过来就错了
            // TODO: 因为如果左边先走，left停住的位置必然大于base，而先从right开始，right必定停住小于base的位置，且保证left<=right，所以不会出现从left出发时导致的left位置大于base的情况
            while (left < right && base <= nums[right]) {
                right--;
            }
            while (left < right && base >= nums[left]) {
                left++;
            }
            swap(
                nums,
                left,
                right
            );
            swap(
                originIndex,
                left,
                right
            );
        }
        // 更新基准位置
        swap(nums, baseIndex, left);
        swap(originIndex, baseIndex, left);
        return left;
    }

    public void quickSort(int[] nums, int[] originIndex, int left, int right) {
        if (left < right) {
            int baseIndex = partition(nums, originIndex, left, right);
            quickSort(nums, originIndex, left, baseIndex - 1);
            quickSort(nums, originIndex, baseIndex + 1, right);
        }
    }

    public void swap(int[] data, int left, int right) {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }
}
