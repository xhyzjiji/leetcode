package com.meituan.xhyzjiji.number.algorithmsFourthEdition.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

public class BinarySearchTree<KEY extends Comparable, VALUE> implements SearchTree<KEY, VALUE> {

    private TreeNode<KEY, VALUE> rootNode;

    public BinarySearchTree() {
        this.rootNode = null;
    }

    public BinarySearchTree(BinarySearchTree tree) {
        this.rootNode = tree.rootNode;
    }

    /**
     * 小于key的结点数 + 1
     * @param key
     * @return
     */
    @Override
    public int rank(KEY key) {
        KEY floorKey = floor(key);
        if (floorKey == null) {
            return 0;
        } else {
            if (floorKey.equals(key)) {
                return size(key) - 1;
            } else {
                return size(key);
            }
        }
    }

    /**
     * 以key为根结点的子树大小
     * @param key
     * @return
     */
    @Override
    public int size(KEY key) {
        TreeNode node = getNode(key);
        if (node != null) {
            return node.getSize();
        } else {
            return 0;
        }
    }

    /**
     * 返回小于等于key的最大key
     * @param key
     * @return
     */
    @Override
    public KEY floor(KEY key) {
        TreeNode<KEY, VALUE> floorNode = floor(key, rootNode);
        return floorNode != null ? floorNode.getKey() : null;
    }

    private TreeNode<KEY, VALUE> floor(KEY key, TreeNode node) {
        if (node == null) {
            return node;
        }
        int compRes = key.compareTo(node.getKey());
        if (compRes > 0) {
            TreeNode floorNode = floor(key, node.getrNode());
            return floorNode == null ? node : floorNode;
        } else if (compRes < 0) {
            return floor(key, node.getlNode());
        } else {
            return node;
        }
    }

    /**
     * 返回大于等于key的最小key
     * @param key
     * @return
     */
    @Override
    public KEY ceil(KEY key) {
        TreeNode<KEY, VALUE> ceilNode = ceil(key, rootNode);
        return ceilNode != null ? ceilNode.getKey() : null;
    }

    private TreeNode<KEY, VALUE> ceil(KEY key, TreeNode node) {
        if (node == null) {
            return null;
        }
        int compRes = key.compareTo(node.getKey());
        if (compRes > 0) {
            return ceil(key, node.getrNode());
        } else if (compRes < 0) {
            TreeNode ceilNode = ceil(key, node.getlNode());
            return ceilNode == null ? node : ceilNode;
        } else {
            return node;
        }
    }

    @Override
    public boolean isExist(KEY key) {
        TreeNode node = getNode(key);
        return node != null;
    }

    private TreeNode getNode(KEY key) {
        return getNode(key, rootNode);
    }

    private TreeNode getNode(KEY key, TreeNode node) {
        if (node == null) {
            return null;
        }
        int compRes = key.compareTo(node.getKey());
        if (compRes > 0) {
            return getNode(key, node.getrNode());
        } else if (compRes < 0) {
            return getNode(key, node.getlNode());
        } else {
            return node;
        }
    }

    @Override
    public VALUE search(KEY key) {
        return search(key, rootNode);
    }

    private VALUE search(KEY key, TreeNode<KEY, VALUE> node) {
        if (node == null) {
            return null;
        }
        int compRes = key.compareTo(node.getKey());
        if (compRes > 0) {
            return search(key, (TreeNode<KEY, VALUE>) node.getrNode());
        } else if (compRes < 0) {
            return search(key, (TreeNode<KEY, VALUE>) node.getlNode());
        } else {
            return node.getValue();
        }
    }

    /**
     * 返回大于等于key的offset后limit个结点
     * @param key
     * @param offset
     * @param limit
     * @param isAscOrder
     * @return
     */
    @Override
    public List<VALUE> search(KEY key, int offset, int limit, boolean isAscOrder) {
        KEY ceilKey = ceil(key);
        if (ceilKey == null) {
            return new ArrayList<>();
        } else {
            List<TreeNode> ans = new ArrayList<>();
            midOrderedVisit(this.rootNode, key, new Limitation(offset, limit), ans);

            List<VALUE> res = new ArrayList<>();
            for (TreeNode<KEY, VALUE> node : ans) {
                res.add(node.getValue());
            }
            return res;
        }
    }

    private List<TreeNode> midOrderedVisit(TreeNode<KEY, VALUE> node, KEY key, Limitation limitation, List<TreeNode> ans) {
        if (node == null || limitation.getLimit() <= 0) {
            return ans;
        }
        int compRes = node.getKey().compareTo(key);
        if (compRes > 0) {
            midOrderedVisit(node.getlNode(), key, limitation, ans);
            if (limitation.getLimit() > 0) {
                if (limitation.getOffset() > 0) {
                    limitation.decreaseOffset();
                } else {
                    ans.add(node);
                    limitation.decreaseLimit();
                }
            } else {
                return ans;
            }
            midOrderedVisit(node.getrNode(), key, limitation, ans);
        } else if (compRes < 0) {
            midOrderedVisit(node.getrNode(), key, limitation, ans);
        } else {
            if (limitation.getLimit() > 0) {
                if (limitation.getOffset() > 0) {
                    limitation.decreaseOffset();
                } else {
                    ans.add(node);
                    limitation.decreaseLimit();
                }
            } else {
                return ans;
            }
            midOrderedVisit(node.getrNode(), key, limitation, ans);
        }
        return ans;
    }

    @Override
    public List<VALUE> searchAll(boolean isAscOrder) {
        return null;
    }

    @Override
    public boolean insert(KEY key, VALUE value) {
        if (rootNode == null) {
            rootNode = new TreeNode<KEY, VALUE>(key, value, null, null, 1) {};
            return true;
        } else {
            return insert(key, value, rootNode);
        }
    }
    /**
     *
     * @param key
     * @param value
     * @param node notnull
     * @return key已存在则返回false
     */
    private boolean insert(KEY key, VALUE value, TreeNode<KEY, VALUE> node) {
        int compRes = key.compareTo(node.getKey());
        if (compRes > 0) {
            if (node.getrNode() == null) {
                node.setrNode(new TreeNode<KEY, VALUE>(key, value, null, null, 1) {});
                node.setSize(node.getSize() + 1);
                return true;
            } else {
                boolean insertRes = insert(key, value, node.getrNode());
                if (insertRes) {
                    node.setSize(node.getSize() + 1);
                }
                return insertRes;
            }
        } else if (compRes < 0) {
            if (node.getlNode() == null) {
                node.setlNode(new TreeNode<KEY, VALUE>(key, value, null, null, 1) {});
                node.setSize(node.getSize() + 1);
                return true;
            } else {
                boolean insertRes = insert(key, value, node.getlNode());
                if (insertRes) {
                    node.setSize(node.getSize() + 1);
                }
                return insertRes;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean update(KEY key, VALUE value) {
        TreeNode<KEY, VALUE> node = getNode(key);
        if (node == null) {
            return false;
        } else {
            node.setValue(value);
            return true;
        }
    }

    @Override
    public boolean delete(KEY key) {
        boolean res = false;

        TreeNode KeyNode = getNode(key);
        if (KeyNode == null) {
            return false;
        }

        TreeNode<KEY, VALUE> parentNode = null;
        TreeNode<KEY, VALUE> node = this.rootNode;
        int prevCompRes = 0;
        int compRes;
        while (node != null) {
            compRes = key.compareTo(node.getKey());
            if (compRes > 0) {
                node.setSize(node.getSize() - 1);
                parentNode = node;
                node = node.getrNode();
            } else if (compRes < 0) {
                node.setSize(node.getSize() - 1);
                parentNode = node;
                node = node.getlNode();
            } else {
                TreeNode subsituteNode;
                if (node.getlNode() != null) {
                    subsituteNode = deleteMax(node.getlNode());
                } else if (node.getrNode() != null) {
                    subsituteNode = deleteMin(node.getrNode());
                } else {
                    subsituteNode = null;
                }

                if (parentNode == null) {
                    this.rootNode = subsituteNode;
                } else {
                    // 用subsituteNode替换结点node
                    assert prevCompRes != 0;
                    if (prevCompRes > 0) {
                        parentNode.setrNode(subsituteNode);
                    } else if (prevCompRes < 0) {
                        parentNode.setlNode(subsituteNode);
                    }
                }
                if (subsituteNode != null) {
                    subsituteNode.setSize(node.getSize() - 1);
                    subsituteNode.setlNode(node.getlNode() == subsituteNode ? subsituteNode.getlNode() : node.getlNode());
                    subsituteNode.setrNode(node.getrNode() == subsituteNode ? subsituteNode.getrNode() : node.getrNode());
                }
                res = true;
                break;
            }
            prevCompRes = compRes;
        }

        return res;
    }

    /**
     *
     * @param node
     * @return 返回被删除结点
     */
    private TreeNode deleteMin(TreeNode node) {
        TreeNode parentNode = null;
        while (node != null) {
            if (node.getlNode() != null) {
                parentNode = node;
                node.setSize(node.getSize() - 1);
                node = node.getlNode();
            } else {
                if (parentNode != null) {
                    parentNode.setlNode(node.getrNode());
                }
                break;
            }
        }

        return node;
    }

    private TreeNode deleteMax(TreeNode node) {
        TreeNode parentNode = null;
        while (node != null) {
            if (node.getrNode() != null) {
                parentNode = node;
                node.setSize(node.getSize() - 1);
                node = node.getrNode();
            } else {
                if (parentNode != null) {
                    parentNode.setrNode(node.getlNode());
                }
                break;
            }
        }

        return node;
    }

    /**
     * 水平遍历树
     * |||||A
     * |||/||\
     * ||B||||D
     * |/\|||/\
     * C|E||F|G
     */
    @Override
    public void drawTree() {
        System.out.println("========================================================================");
        StringBuilder treeStr = new StringBuilder();

        int height = getHeight();
        final String SPLIT_SYMBOL = "     ";
        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(this.rootNode);
        List<TreeNode> nextNodes = new ArrayList<>();

        while (height > 0 && CollectionUtils.isEmpty(nodes) == false) {
            treeStr.append(genSplitSymbol((int) Math.pow(2, height-1) - 1, SPLIT_SYMBOL));
            for (TreeNode node : nodes) {
                if (node != null) {
                    treeStr.append(node.getValue() == null ? "  X  " : node.getValue() + "(" + node.getSize() + ")")
                        .append(genSplitSymbol((int) Math.pow(2, height)-1, SPLIT_SYMBOL));
                    nextNodes.add(node.getlNode());
                    nextNodes.add(node.getrNode());
                } else {
                    treeStr.append("  X  ").append(genSplitSymbol((int) Math.pow(2, height)-1, SPLIT_SYMBOL));
                    nextNodes.add(null);
                    nextNodes.add(null);
                }
            }
            treeStr.append("\n");
            nodes.clear();
            nodes.addAll(nextNodes);
            nextNodes.clear();
            height--;
        }
        System.out.println(treeStr.toString());
        System.out.println("========================================================================");
    }

    private String genSplitSymbol(int times, String splitter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(splitter);
        }
        return sb.toString();
    }

    private int getHeight() {
        return getHeight(rootNode);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.getlNode());
        int rightHeight = getHeight(node.getrNode());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 根 左 右
     * @return
     */
    private List<TreeNode> prevVisit() {
        List<TreeNode> res = new ArrayList<>();
        return prevVisit(rootNode, res);
    }
    private List<TreeNode> prevVisit(TreeNode node, List<TreeNode> ans) {
        if (node == null) {
            return ans;
        }
        ans.add(node);
        prevVisit(node.getlNode(), ans);
        prevVisit(node.getrNode(), ans);
        return ans;
    }

    /**
     * 左 根 右
     * @return
     */
    private List<TreeNode> midVisit() {
        List<TreeNode> res = new ArrayList<>();
        return midVisit(rootNode, res);
    }
    private List<TreeNode> midVisit(TreeNode node, List<TreeNode> ans) {
        if (node == null) {
            return ans;
        }
        midVisit(node.getlNode(), ans);
        ans.add(node);
        midVisit(node.getrNode(), ans);
        return ans;
    }

    /**
     * 左 右 根
     * @return
     */
    private List<TreeNode> postVisit() {
        List<TreeNode> res = new ArrayList<>();
        return postVisit(rootNode, res);
    }
    private List<TreeNode> postVisit(TreeNode node, List<TreeNode> ans) {
        if (node == null) {
            return ans;
        }
        postVisit(node.getlNode(), ans);
        postVisit(node.getrNode(), ans);
        ans.add(node);
        return ans;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> binTree = new BinarySearchTree<>();

        binTree.insert(50, 50);
        binTree.insert(30, 30);
        binTree.insert(10, 10);
        binTree.insert(20, 20);
        binTree.insert(70, 70);
        binTree.insert(90, 90);
        binTree.insert(80, 80);
        binTree.insert(60, 60);
        binTree.insert(15, 15);
        binTree.insert(55, 55);
        binTree.insert(58, 58);
        binTree.insert(65, 65);
        binTree.insert(63, 63);
        binTree.insert(40, 40);
        binTree.insert(35, 35);
        binTree.insert(33, 33);
        binTree.drawTree();

        System.out.println(binTree.size(3));
        System.out.println(binTree.size(8));
        System.out.println(binTree.size(10));

        System.out.println(Arrays.toString(binTree.search(21, 0, 5, false).toArray()));

        binTree.delete(30);
        binTree.drawTree();
        binTree.delete(50);
        binTree.drawTree();
        binTree.delete(70);
        binTree.drawTree();
        binTree.delete(63);
        binTree.drawTree();
        binTree.delete(58);
        binTree.drawTree();

        binTree.delete(40);
        binTree.drawTree();
        binTree.delete(35);
        binTree.drawTree();
        /*
        ========================================================================
                                   33(9)
               20(3)                                   65(5)
     10(2)                 X                 60(2)               90(2)
  X       15(1)       X         X       55(1)       X       80(1)       X
========================================================================
         */
        binTree.delete(33);
        binTree.drawTree();
        while (binTree.rootNode != null) {
            binTree.delete(binTree.rootNode.getKey());
            binTree.drawTree();
        }
    }

}
