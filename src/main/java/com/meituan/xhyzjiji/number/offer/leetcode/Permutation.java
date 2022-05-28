package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 面试题 08.07. 无重复字符串的排列组合
 * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 *
 * 示例1:
 *
 *  输入：S = "qwe"
 *  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
 * 示例2:
 *
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 * 提示:
 *
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 */
public class Permutation {

    public String[] permutation(String S) {
        List<String> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        boolean[] visited = new boolean[S.length()];
        char[] chars = S.toCharArray();
        internalPermutation(res, chars, stringBuilder, visited);
        return res.toArray(new String[0]);
    }

    public void internalPermutation(List<String> res, char[] chars, StringBuilder stringBuilder, boolean[] visited) {
        // 终止迭代
        if (stringBuilder.length() == chars.length) {
            res.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < chars.length; ++i) {
            if (visited[i] == false) {
                stringBuilder.append(chars[i]);
                visited[i] = true;
                internalPermutation(res, chars, stringBuilder, visited);

                visited[i] = false;
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
    }

}
