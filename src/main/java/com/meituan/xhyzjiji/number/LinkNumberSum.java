package com.meituan.xhyzjiji.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkNumberSum {

    private static final Logger log = LoggerFactory.getLogger(LinkNumberSum.class);

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        LinkNumberSum solution = new LinkNumberSum();

        int[] inputLeft = new int[] {2, 4, 3};
        int[] inputRight = new int[] {5, 6, 4};
        ListNode leftNode = null;
        for (int i = 0; i < inputLeft.length; i++) {
            ListNode node = new ListNode(inputLeft[i]);
            node.next = leftNode;
            leftNode = node;
        }
        ListNode rightNode = null;
        for (int i = 0; i < inputRight.length; i++) {
            ListNode node = new ListNode(inputRight[i]);
            node.next = rightNode;
            rightNode = node;
        }

        ListNode ans = solution.addTwoNumbers(leftNode, rightNode);
        List<Integer> ansArray = new ArrayList<Integer>();
        while (ans != null) {
            ansArray.add(ans.val);
            ans = ans.next;
        }
        log.info("ans = {}", Arrays.toString(ansArray.toArray()));
    }

    //6 ms, 44.5 MB
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int s = 0;
        int c = 0;
        ListNode head = null;
        ListNode tail = null;
        while (l1 != null || l2 != null) {
            int sum;
            if (l1 != null && l2 != null) {
                sum = l1.val + l2.val + c;
            } else {
                sum = l1 == null ? l2.val + c : l1.val + c;
            }

            if (sum >= 10) {
                s = sum - 10;
                c = 1;
            } else {
                s = sum;
                c = 0;
            }

            ListNode node = new ListNode(s);
            if (head == null) {
                head = node;
            }
            if (tail != null) {
                tail.next = node;
            }
            tail = node;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (c != 0) {
            ListNode node = new ListNode(c);
            tail.next = node;
        }

        return head;
    }


     /* Definition for singly-linked list. */
     public static class ListNode {
         int val;
         ListNode next;

         ListNode(int x) {
             val = x;
         }
     }

}
