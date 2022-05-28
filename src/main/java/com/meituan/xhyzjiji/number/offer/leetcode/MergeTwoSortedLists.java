package com.meituan.xhyzjiji.number.offer.leetcode;

public class MergeTwoSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(
            int val,
            ListNode next
        ) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode prev = null, curr = null;
        while (list1 != null && list2 != null) {
            int v1 = list1.val;
            int v2 = list2.val;
            if (v1 <= v2) {
                curr = list1;
                list1 = list1.next;
            } else {
                curr = list2;
                list2 = list2.next;
            }
            if (prev != null) {
                prev.next = curr;
            }
            prev = curr;
            curr.next = null;
            if (head == null) {
                head = prev;
            }
        }
        while (list1 != null) {
            if (prev != null) {
                prev.next = list1;
            }
            prev = list1;
            list1 = list1.next;
        }
        while (list2 != null) {
            if (prev != null) {
                prev.next = list2;
            }
            prev = list2;
            list2 = list2.next;
        }
        return head;
    }

    public static ListNode createList(int[] nums) {
        ListNode head = null;
        ListNode tail = null;
        for (int num : nums) {
            ListNode listNode = new ListNode(num);
            if (head == null) {
                head = listNode;
            }
            if (tail != null) {
                tail.next = listNode;
            }
            tail = listNode;
        }
        return head;
    }

    public static void printList(ListNode l) {
        while (l != null) {
            System.out.print(l.val + ",");
            l = l.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        ListNode l1 = createList(new int[]{1,2,4});
        ListNode l2 = createList(new int[]{1,3,4});

        ListNode merged = mergeTwoLists(l1, l2);
        printList(merged);
    }
}
