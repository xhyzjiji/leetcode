package com.meituan.xhyzjiji.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MostPalindromicString {

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    */

    private static final Logger log = LoggerFactory.getLogger(MostPalindromicString.class);

    public static void main(String[] args) {
        MostPalindromicString solution = new MostPalindromicString();

        String tc1 = "babad";
        String tc2 = "cbbd";

        log.info("{} palindromic string is {}", tc1, longestPalindrome(tc1));
        log.info("{} palindromic string is {}", tc2, longestPalindrome(tc2));
    }

    // 6ms, 35.6MB
    // 思路：先找相同字符子串，再左右搜索重复字符，组成最长回文子串
    public static String longestPalindrome(String s) {
        byte[] originByteArray = s.getBytes();

        int nextIndex = 0;
        int palindromicHeaderIndex = 0;
        int maxLen = 0;
        while (nextIndex < originByteArray.length) {
            int tempMaxLen = 0;

            int tempHeaderIndex = nextIndex;
            int tempHeaderExtendIndex = nextIndex;
            // find same substr
            byte tempHeader = originByteArray[tempHeaderIndex];
            while (tempHeaderExtendIndex < originByteArray.length && originByteArray[tempHeaderExtendIndex] == tempHeader) {
                tempHeaderExtendIndex++;
                tempMaxLen++;
            }
            nextIndex = tempHeaderExtendIndex;

            int left = tempHeaderIndex - 1;
            int right = tempHeaderExtendIndex;
            while (left >= 0 && right < originByteArray.length) {
                if (originByteArray[left] == originByteArray[right]) {
                    tempMaxLen += 2;
                    tempHeaderIndex = left;
                } else {
                    break;
                }
                left--;
                right++;
            }

            if (tempMaxLen > maxLen) {
                palindromicHeaderIndex = tempHeaderIndex;
                maxLen = tempMaxLen;
            }
        }

        return s.substring(palindromicHeaderIndex, palindromicHeaderIndex + maxLen);
    }

}
