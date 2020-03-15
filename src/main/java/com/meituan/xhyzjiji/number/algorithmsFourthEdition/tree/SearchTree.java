package com.meituan.xhyzjiji.number.algorithmsFourthEdition.tree;

import java.util.List;

public interface SearchTree<KEY extends Comparable, VALUE> {

    int rank(KEY key);
    int size(KEY key);
    KEY floor(KEY key);
    KEY ceil(KEY key);

    boolean isExist(KEY key);
    VALUE search(KEY key);
    List<VALUE> search(KEY key, int offset, int limit, boolean isAscOrder);
    List<VALUE> searchAll(boolean isAscOrder);
    boolean insert(KEY key, VALUE value);
    boolean update(KEY key, VALUE value);
    boolean delete(KEY key);

    // 水平遍历树
    void drawTree();
}
