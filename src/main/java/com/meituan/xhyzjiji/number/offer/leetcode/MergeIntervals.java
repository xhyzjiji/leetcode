package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        ans.add(intervals[0]);
        int[] lastMergedInterval = intervals[0];
        for (int i = 1; i < intervals.length; ++i) {
            int[] interval = intervals[i];
            if (interval[0] > lastMergedInterval[1]) {
                lastMergedInterval = interval;
                ans.add(interval);
            } else {
                lastMergedInterval[1] = Math.max(lastMergedInterval[1], interval[1]);
            }
        }
        return ans.toArray(new int[0][0]);
    }

}
