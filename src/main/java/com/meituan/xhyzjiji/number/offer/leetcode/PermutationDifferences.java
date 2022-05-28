package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermutationDifferences {

    // 组合，用dfs
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permutation(int n, int k) {
        boolean[] visited = new boolean[n+1];
        internalPermutation(new LinkedList<>(), n, k, visited);
        return res;
    }
    public void internalPermutation(LinkedList<Integer> subList, int n, int k, boolean[] visited) {
        if (subList.size() == k) {
            List<Integer> elem = new ArrayList<>();
            elem.addAll(subList);
            res.add(elem);
            return;
        }
        for (int i = 1; i <= n; ++i) {
            if (visited[i] == false) {
                subList.add(i);
                visited[i] = true;
                internalPermutation(subList, n, k, visited);
                subList.removeLast();
                visited[i] = false;
            }
        }
    }

    // 排列，顺序访问逐个加入，因为是顺序访问，不存在访问前面的数据，所以不需要visited了
    public List<List<Integer>> combine(int n, int k) {
        internalCombine(new LinkedList<>(), n, k, 1);
        return res;
    }
    public void internalCombine(LinkedList<Integer> subList, int n, int k, int start) {
        if (subList.size() == k) {
            List<Integer> elem = new ArrayList<>();
            elem.addAll(subList);
            res.add(elem);
            return;
        }
        for (int i = start; i <= n; ++i) {
            subList.add(i);
            internalCombine(subList, n, k, i + 1);
            subList.removeLast();
        }
    }

}
