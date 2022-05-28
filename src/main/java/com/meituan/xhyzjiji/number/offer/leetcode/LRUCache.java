package com.meituan.xhyzjiji.number.offer.leetcode;

import java.util.Iterator;
import java.util.LinkedHashMap;

class LRUCache {
    int capacity;
    LinkedHashMap<Integer, Integer> cache;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 1.0F, true);
    }

    // 新节点加在尾部，accessOrder=true访问后会移到尾部
    public int get(int key) {
        int res = -1;
        if (cache.containsKey(key)) {
            res = cache.get(key);
        }
        return res;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key, value);
        } else {
            cache.put(key, value);
            if (cache.size() > capacity) {
                Iterator it = cache.keySet().iterator();
                it.next();
                it.remove();
            }
        }
    }
}
