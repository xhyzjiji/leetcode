package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import com.meituan.xhyzjiji.number.utils.QuickSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class FindTwoSum {

    public static class Solution {
        public List<Pair<Integer, Integer>> twoSum(int[] nums) {
            List<Pair<Integer, Integer>> res = new ArrayList<>();
            QuickSort.quickSort(nums, 0, nums.length - 1);
            int left = 0;
            int right = nums.length - 1;
            int preLeft;
            int preRight;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == 0) {
                    preLeft = nums[left];
                    preRight = nums[right];
                    res.add(Pair.of(preLeft, preRight));
                    while (left < right && nums[left] == preLeft) {
                        left++;
                    }
                    while (left < right && nums[right] == preRight) {
                        right--;
                    }
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] tc = new int[] {-5,-5,-4,-4,-3,-2,-1,0,1,1,2,2,3,3,4,4,5};
        List<Pair<Integer, Integer>> res = solution.twoSum(tc);
        for (Pair<Integer, Integer> ans : res) {
            System.out.println(ans.getLeft() + ", " + ans.getRight());
        }
    }

}
