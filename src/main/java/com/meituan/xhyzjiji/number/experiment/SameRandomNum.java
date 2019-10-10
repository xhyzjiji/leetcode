package com.meituan.xhyzjiji.number.experiment;

import com.meituan.xhyzjiji.number.std.StdRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SameRandomNum {

    /**
     * T = 1000
     * {10000=110.532, 100000=9954.111, 1000=1.145, 1000000=404978.786}
     * T = 500
     * {10000=109.324, 100000=9944.016, 1000=1.206, 1000000=404984.438}
     * T = 100
     * {10000=109.85, 100000=9962.82, 1000=0.96, 1000000=405069.18}
     * T = 50
     * {10000=107.1, 100000=9962.66, 1000=1.06, 1000000=405010.26}
     * @param args
     */
    public static void main(String[] args) {
        int[] tcs = new int[] {1000, 10000, 100000, 1000000};
        Set<Integer> s1 = new HashSet<Integer>();
        Set<Integer> s2 = new HashSet<Integer>();

        Map<Integer/*tc*/, Double> e = new HashMap<Integer, Double>();
        int T = 1000;
        for (int tc : tcs) {
            int count = 0;
            for (int k = 0; k < T; k++) {
                s1.clear();
                s2.clear();
                for (int i = 0; i < tc; i++) {
                    int newNum = StdRandom.uniform(900000) + 100000;
                    s1.add(newNum);
                }
                for (int i = 0; i < tc; i++) {
                    int newNum = StdRandom.uniform(900000) + 100000;
                    s2.add(newNum);
                }

                for (Integer num : s1) {
                    if (s2.contains(num)) {
                        count++;
                    }
                }
            }
            e.put(tc, (double)count / T);
        }
        System.out.println(e.toString());
    }

}
