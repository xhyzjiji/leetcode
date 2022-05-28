package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.Stack;

/**
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 *
 *  
 * 示例 1 ：
 *
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * 示例 2 ：
 *
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 ：
 *
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 *  
 *
 * 提示：
 *
 * 1 <= k <= num.length <= 105
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : num.toCharArray()) {
            if (stack.empty() || k == 0) {
                stack.push(c);
            } else {
                while (stack.empty() == false && k > 0) {
                    char elem = stack.peek();
                    if (elem > c) {
                        stack.pop();
                        --k;
                    } else {
                        break;
                    }
                }
                stack.push(c);
            }
        }
        Stack<Character> s2 = new Stack<>();
        while (stack.empty() == false) {
            if (k > 0) {
                stack.pop();
                --k;
            } else {
                s2.push(stack.pop());
            }
        }
        while (s2.empty() == false) {
            char p = s2.pop();
            if (stringBuilder.length() > 0 || p != '0') {
                stringBuilder.append(p);
            }
        }
        return stringBuilder.length() > 0 ? stringBuilder.toString() : "0";
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("10001", 4));
    }
}
