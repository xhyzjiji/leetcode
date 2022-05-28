package com.meituan.xhyzjiji.number.offer.leetcode;

public class ConstructBinaryTreeByPreorderInorder {

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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 前序第一个元素把中序切成左右子树
        return buildSubTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildSubTree(int[] preorder, int pleft, int pright, int[] inorder, int ileft, int iright) {
        if (pleft > pright) {
            return null;
        }
        if (pleft == pright) {
            return new TreeNode(preorder[pleft], null, null);
        }
        int root = preorder[pleft];
        int i;
        for (i = ileft; i <= iright; ++i) {
            if (inorder[i] == root) {
                break;
            }
        }
        TreeNode rootNode = new TreeNode(root);
        int leftLength = i - ileft;
        int rightLength = iright - i;
        rootNode.left = buildSubTree(preorder, pleft + 1, pleft + leftLength, inorder, ileft, i - 1);
        rootNode.right = buildSubTree(preorder, pleft + leftLength + 1, pright, inorder, i + 1, iright);
        return rootNode;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeByPreorderInorder constructor = new ConstructBinaryTreeByPreorderInorder();
        int[] preorder = new int[] {1,2,3};
        int[] inorder = new int[] {3,2,1};
        constructor.buildTree(preorder, inorder);
    }

}
