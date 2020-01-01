package com.meituan.xhyzjiji.number.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomRank {

    public static void main(String[] args) {
        final int len = 6;
        final int testCnt = 2400000;
        Random random = new Random();
        List<Integer> originalArray = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            originalArray.add(i + 1);
        }

        Map<List<Integer>, Integer> counter = new HashMap<>();
        int testNum = testCnt;
        while (testNum > 0) {
            List<Integer> targetArray = new ArrayList<>(originalArray);
            for (int i = 0; i < len * 2; i++) {
                int swapI = random.nextInt(len);
                int swapJ = random.nextInt(len);
                if (swapI != swapJ) {
                    int temp = targetArray.get(swapI);
                    targetArray.set(swapI, targetArray.get(swapJ));
                    targetArray.set(swapJ, temp);
                }
            }

            if (counter.get(targetArray) != null) {
                counter.put(targetArray, counter.get(targetArray) + 1);
            } else {
                counter.put(targetArray, 1);
            }

            testNum--;
        }

        for (Map.Entry<List<Integer>, Integer> entry : counter.entrySet()) {
            System.out.println(entry.getKey().toString() + " : " + entry.getValue() * 1.0 / testCnt);
        }
    }

}
