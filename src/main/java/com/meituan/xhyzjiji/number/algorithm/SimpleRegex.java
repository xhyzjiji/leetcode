package com.meituan.xhyzjiji.number.algorithm;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class SimpleRegex {

    // TODO: 题目意思是贪婪匹配？
    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     * 说明:
     *
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 示例 1:
     *
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * 示例 3:
     *
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * 示例 4:
     *
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5:
     *
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/regular-expression-matching
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /*public static class Solution {
        public boolean isMatch(String s, String p) {
            if (p.isEmpty()) {
                if (s.isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                int sIndex = 0;
                int pIndex = 0;
                char[] sChars = s.toCharArray();
                char[] pChars = p.toCharArray();

                boolean isAnyKey;
                boolean isRepeat;
                char currentKey;
                List<Character> repeatUntilSubString = new ArrayList<Character>();
                while (pIndex < pChars.length && sIndex < sChars.length) {
                    isAnyKey = false;
                    isRepeat = false;
                    currentKey = pChars[pIndex++];

                    if (currentKey == '.') {
                        isAnyKey = true;
                    }
                    if (pIndex < pChars.length) {
                        if (pChars[pIndex] == '*') {
                            pIndex++; // 消耗掉*字符
                            if (pIndex >= pChars.length) {
                                // p以.*结尾
                                if (isAnyKey) {
                                    return true;
                                } else {
                                    isRepeat = true;
                                }
                            } else {
                                isRepeat = true;
                                repeatUntilSubString.clear();
                                int tempPIndex = pIndex;
                                while (tempPIndex < pChars.length) {
                                    char tempP = pChars[tempPIndex];

                                    if (tempP == '.' || tempP == '*') {
                                        break;
                                    } else {
                                        repeatUntilSubString.add(tempP);
                                        tempPIndex++;
                                    }
                                }
                            }
                        }
                    }

                    // s = abcdefcdefcdef, p = ab.*cdef 要解决这个问题
                    if (isAnyKey && isRepeat) {
                        while (sIndex < sChars.length) {
                            int tempSIndex = sIndex;
                            int tempLIndex = 0;
                            boolean isBreak = false;
                            while (tempSIndex < sChars.length && tempLIndex < repeatUntilSubString.size()) {
                                if (sChars[tempSIndex] != repeatUntilSubString.get(tempLIndex)) {
                                    isBreak = true;
                                    break;
                                }
                            }

                            if (isBreak) {
                                sIndex++;
                            } else {
                                break;
                            }
                        }
                    } else if (isAnyKey) {
                        sIndex++;
                    } else if (isRepeat) {
                        if (isRepeatUntilEnd) {
                            while (sIndex < sChars.length) {
                                if (sChars[sIndex++] != currentKey) {
                                    return false;
                                }
                            }
                        } else {
                            while (sIndex < sChars.length) {
                                if (sChars[sIndex] == repeatUntilKey) {
                                    break;
                                } else if (sChars[sIndex] != currentKey) {
                                    if (repeatUntilKey != '.') {
                                        return false;
                                    } else {
                                        break;
                                    }
                                }
                                sIndex++;
                            }
                        }
                    } else {
                        if (sChars[sIndex++] != currentKey) {
                            return false;
                        }
                    }
                }

                return sIndex >= sChars.length && pIndex >= pChars.length;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Pair<String, String>> testCases = new ArrayList<Pair<String, String>>() {{
//            add(Pair.of("aa", "a"));
//            add(Pair.of("aa", "a*"));
//            add(Pair.of("ab", ".*"));
//            add(Pair.of("aab", "c*a*b"));
//            add(Pair.of("mississippi", "mis*is*p*."));
//            add(Pair.of("mississippi", "mis*is*ip*."));
//            add(Pair.of("abcd", "d*"));
            add(Pair.of("aaa", "a*a"));
        }};
        for (Pair<String, String> tc : testCases) {
            System.out.println(tc.getLeft() + " -> " + tc.getRight() + " : " + solution.isMatch(tc.getLeft(), tc.getRight()));
        }
    }*/

}
