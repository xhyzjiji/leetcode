package com.meituan.xhyzjiji.number.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongestSubStrWithNonReaptedChar {

    private static final Logger log = LoggerFactory.getLogger(LongestSubStrWithNonReaptedChar.class);
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        LongestSubStrWithNonReaptedChar solution = new LongestSubStrWithNonReaptedChar();

        log.info("longest len of \"{}\" is {}", "pwwkew", solution.lengthOfLongestSubstring("pwwkew"));
        log.info("longest len of \"{}\" is {}", "bbbbb", solution.lengthOfLongestSubstring("bbbbb"));
        log.info("longest len of \"{}\" is {}", "abcabcbb", solution.lengthOfLongestSubstring("abcabcbb"));
        log.info("longest len of \"{}\" is {}", " ", solution.lengthOfLongestSubstring(" "));

        log.info("longest len of \"{}\" is {}", "pwwkew", solution.lengthOfLongestSubstring2("pwwkew"));
        log.info("longest len of \"{}\" is {}", "bbbbb", solution.lengthOfLongestSubstring2("bbbbb"));
        log.info("longest len of \"{}\" is {}", "abcabcbb", solution.lengthOfLongestSubstring2("abcabcbb"));
        log.info("longest len of \"{}\" is {}", " ", solution.lengthOfLongestSubstring2(" "));
    }

    // 5 ms, 38.7 MB
    public int lengthOfLongestSubstring3(String s) {
        int ans = 0;

        char[] charArray = s.toCharArray();
        int[] indexArray = new int[128];
        for (int i = 0; i < indexArray.length; i++) {
            indexArray[i] = -1;
        }
        int subStrHeaderIndex = 0;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (indexArray[c] != -1 && indexArray[c] >= subStrHeaderIndex) {
                int length = i - subStrHeaderIndex;
                ans = length > ans ? length : ans;
                subStrHeaderIndex = indexArray[c] + 1;
            }
            indexArray[c] = i; // update index
        }
        int length = charArray.length - subStrHeaderIndex;
        ans = length > ans ? length : ans;
        return ans;
    }

    // 27 ms, 38.0 MB
    public int lengthOfLongestSubstring2(String s) {
        int ans = 0;
        char[] charArray = s.toCharArray();
        Map<Character, Integer> map = new HashMap();
        int subStrHeaderIndex = 0;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (map.containsKey(c) && map.get(c) >= subStrHeaderIndex) {
                int length = i - subStrHeaderIndex;
                ans = length > ans ? length : ans;
                subStrHeaderIndex = map.get(c) + 1;
            }
            map.put(c, i); // update index
        }
        int length = charArray.length - subStrHeaderIndex;
        ans = length > ans ? length : ans;
        return ans;
    }

    // 52 ms, 42.9 MB
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;

        int subStrHeaderIndex = 0;
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        List<Character> currentSubStr = new ArrayList<Character>();
        /*List<Byte> longestSubStr = new ArrayList<Byte>();*/
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char b = charArray[i];
            if (map.containsKey(b)) {
                // 结算上一个子串长度
                int length = i - subStrHeaderIndex;
                ans = length > ans ? length : ans;
                subStrHeaderIndex = map.get(b) + 1;
                // 删除上一个重复字符之前的记录
                Iterator<Character> iterator = currentSubStr.iterator();
                while (iterator.hasNext()) {
                    Character delB = iterator.next();
                    iterator.remove();
                    map.remove(delB);
                    if (delB.equals(b)) {
                        break;
                    }
                }
            }
            map.put(b, i);
            currentSubStr.add(b);
        }
        int length = charArray.length - subStrHeaderIndex;
        ans = length > ans ? length : ans;

        return ans;
    }
}
