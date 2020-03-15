package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JosephusCircle {

    private final static Logger log = LoggerFactory.getLogger(JosephusCircle.class);

    // 递归法
    public static int JosephusCal(int N, int M) {
        if (N == 1) {
            return 0;
        } else {
            return (JosephusCal(N - 1, M) + M) % N;
        }
    }

    public static void main(String[] args) {
        Pair<Integer, Integer>[] tcs = new Pair[] {
            Pair.of(10, 4)
        };

        for (Pair<Integer, Integer> tc : tcs) {
            log.info("N={}, M={}, ans={}", tc.getLeft(), tc.getRight(), JosephusCal(tc.getLeft(), tc.getRight()));
        }
    }

}
