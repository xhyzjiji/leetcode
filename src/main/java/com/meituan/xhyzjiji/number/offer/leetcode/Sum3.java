package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sum3 {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);
        int p1,p2,p3;
        for (p1 = 0; p1 < nums.length - 2; p1++) {
            int k = 0 - nums[p1];
            p2 = p1 + 1;
            p3 = nums.length - 1;
            while (p2 < p3) {
                int sum = nums[p2] + nums[p3];
                if (sum < k) {
                    p2++;
                } else if (sum > k) {
                    p3--;
                } else {
                    List<Integer> elem = new ArrayList<>();
                    elem.add(nums[p1]);
                    elem.add(nums[p2]);
                    elem.add(nums[p3]);
                    res.add(elem);
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

}
