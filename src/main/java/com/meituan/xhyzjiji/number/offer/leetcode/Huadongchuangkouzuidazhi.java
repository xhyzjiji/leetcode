package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Huadongchuangkouzuidazhi {

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        LinkedList<Integer> win = new LinkedList<>();
        for (int num : nums) {
            win.add(num);
            priorityQueue.add(num);
            if (win.size() > k) {
                Integer rmObj = win.remove(0);
                priorityQueue.remove(rmObj);
            }
            if (win.size() == k) {
                ans.add(priorityQueue.peek());
            }
        }
        return ans.stream().mapToInt(i->i).toArray();
    }

    /**
     * 维护一个队列，队列内存储窗口k内已排序的元素在数组中的下标
     * 1.超出窗口的元素被移除
     * 2.新加入的元素如果比窗口最小的元素大，则可以移除窗口内最小元素（因为下标比它大，窗口滑动不可能使用这个最小值了）
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length < k) {
            return new int[0];
        }

        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> win = new LinkedList<>();
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            if (win.isEmpty() == false && win.getFirst() <= i - k) {
                win.removeFirst();
            }
            while (win.isEmpty() == false && nums[win.getLast()] < num) {
                win.removeLast();
            }
            win.add(i);

            if (i >= k - 1) {
                ans[i - k + 1] = nums[win.getFirst()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Huadongchuangkouzuidazhi huadongchuangkouzuidazhi = new Huadongchuangkouzuidazhi();
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(huadongchuangkouzuidazhi.maxSlidingWindow2(nums, 3)));
    }

}
