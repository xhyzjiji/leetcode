package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        internalInorderTraversal(root, ans);
        return ans;
    }

    private void internalInorderTraversal(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }

        // 前中后序遍历调整这里的顺序即可
        internalInorderTraversal(root.left, ans);
        ans.add(root.val);
        internalInorderTraversal(root.right, ans);
    }

    // 效率很低，看如何调优
    private void internalInorderTraversalNoRecursion(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while (stack.empty() == false) {
            TreeNode node = stack.peek();
            if (node.left != null && visited.contains(node.left) == false) {
                stack.push(node.left);
            } else {
                ans.add(node.val);
                visited.add(node);
                stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
        }
    }

}
