package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 *
 *  
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2 :
 *
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/QTMn0o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class QTMn0o {
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        // case1:
        // for (int i = 0; i < nums.length; ++i) {
        //     int sum = 0;
        //     for (int j = i; j < nums.length; ++j) {
        //         sum += nums[j];
        //         if (sum == k) {
        //             count++;
        //         }
        //     }
        // }
        // return count;

        // case2:
        // Si = sum(nums(n))|n=0->i
        // Sj-Si = sum(nums(n))|n=i+1->j, 0=<i<=j-1
        // Sj-Si = k, Si = Sj - k, 表示S[i+1,...,j] = k
        int sum = 0;
        Map<Integer, Integer> sCount = new HashMap<>();
        for (int j = 0; j < nums.length; ++j) {
            sum += nums[j];
            int temp = sum - k;

            // 注意条件：i <= j-1，所以不能先把Sj统计到sCount，观测 Si 出现的次数即可
            if (sCount.containsKey(temp)) {
                count += sCount.get(temp);
            }

            if (sCount.containsKey(sum)) {
                sCount.put(sum, sCount.get(sum) + 1);
            } else {
                sCount.put(sum, 1);
            }
        }
        // 为什么要补一个？i = 0 时, S[i+1,...,j] = S[i,...,j], 漏了S[0,...,j]的场景
        if (sCount.containsKey(k)) {
            count += sCount.get(k);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,2,3};
        System.out.println("ans = " + subarraySum(nums, 3));
    }
}
