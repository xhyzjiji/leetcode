package com.meituan.xhyzjiji.number.algorithmsFourthEdition.tree;

public abstract class TreeNode<KEY extends Comparable, VALUE> {

    protected KEY key;
    protected VALUE value;
    protected TreeNode lNode;
    protected TreeNode rNode;
    protected int size; // 包括本结点的子树大小

    public TreeNode(
        KEY key,
        VALUE value,
        TreeNode lNode,
        TreeNode rNode,
        int size
    ) {
        this.key = key;
        this.value = value;
        this.lNode = lNode;
        this.rNode = rNode;
        this.size = size;
    }

    public KEY getKey() {
        return key;
    }

    public void setKey(KEY key) {
        this.key = key;
    }

    public VALUE getValue() {
        return value;
    }

    public void setValue(VALUE value) {
        this.value = value;
    }

    public TreeNode getlNode() {
        return lNode;
    }

    public void setlNode(TreeNode lNode) {
        this.lNode = lNode;
    }

    public TreeNode getrNode() {
        return rNode;
    }

    public void setrNode(TreeNode rNode) {
        this.rNode = rNode;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
