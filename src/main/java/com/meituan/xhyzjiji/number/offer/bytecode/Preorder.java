package com.meituan.xhyzjiji.number.offer.bytecode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Preorder {

    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public static TreeNode createTree(int[] nums) {
        TreeNode root = null;
        boolean init = false;
        Queue<TreeNode> queue = new ArrayDeque<>();

        int i = 0;
        while (i < nums.length) {
            int num = nums[i++];
            TreeNode treeNode = new TreeNode();
            treeNode.setVal(num);

            if (init == false) {
                root = treeNode;
                init = true;
            }
            if (queue.isEmpty()) {
                queue.add(treeNode);
            } else {
                TreeNode parentNode = queue.peek();
                while (parentNode.left != null && parentNode.right != null) {
                    queue.poll();
                    parentNode = queue.peek();
                }
                if (parentNode.left == null) {
                    parentNode.left = treeNode;
                } else if (parentNode.right == null) {
                    parentNode.right = treeNode;
                }
                queue.add(treeNode);
            }
        }
        return root;
    }

    public static void printTree(TreeNode treeRoot) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(treeRoot);
        while (queue.isEmpty() == false) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public static LinkedList<TreeNode> preorder(TreeNode treeRoot) {
        LinkedList<TreeNode> res = new LinkedList<>();

        Set<TreeNode> visited = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeRoot);
        while (stack.empty() == false) {
            TreeNode node = stack.peek();
            if (node.left != null && visited.contains(node.left) == false) {
                stack.push(node.left);
            } else if (node.right != null && visited.contains(node.right) == false) {
                res.add(node);
                visited.add(node);
                stack.pop();
                stack.push(node.right);
            } else {
                res.add(node);
                visited.add(node);
                stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = createTree(new int[]{1,2,3,4,5,6,7});
        printTree(root);

        System.out.println(preorder(root).stream().map(TreeNode::getVal).collect(Collectors.toList()));
    }

}
