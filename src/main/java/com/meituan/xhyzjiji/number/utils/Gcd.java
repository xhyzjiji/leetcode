package com.meituan.xhyzjiji.number.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.tuple.Pair;

public class Gcd {

    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        int x = Math.max(a, b);
        int y = Math.min(a, b);

        int r;
        do {
            r = x % y;
            x = y;
            y = r;
        } while (r > 0);

        return x;
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> tcs = new ArrayList<>();
        tcs.add(Pair.of(100, 100));
        tcs.add(Pair.of(24, 16));
        tcs.add(Pair.of(37, 1));

        for (Pair<Integer, Integer> tc : tcs) {
            System.out.println("gcd(" + tc.getLeft() + ", " + tc.getRight() + ") = " + gcd(tc.getLeft(), tc.getRight()));
        }

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int left = random.nextInt(100);
            int right = random.nextInt(100);
            System.out.println("gcd(" + left + ", " + right + ") = " + gcd(left, right));
        }
    }

}
