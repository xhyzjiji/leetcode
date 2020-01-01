package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import com.meituan.xhyzjiji.number.utils.QuickSort;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Q1_4_15 {

    private static final Logger log = LoggerFactory.getLogger(Q1_4_15.class);

    public static void main(String[] args) {
        int[] tc = new int[] {-5, -4, -4, -3, -2, -1, -1, 0, 1, 2, 2, 3, 4, 5, 5};

        List<ThreeSumAns> threeAns = findThreeSum(tc, 3);
        for (ThreeSumAns threeSumAns : threeAns) {
            log.info("{}, {}, {}", threeSumAns.getA(), threeSumAns.getB(), threeSumAns.getC());
        }
    }

    // 3-sum O(n^2)
    public static List<ThreeSumAns> findThreeSum(int[] arrays, int target) {
        List<ThreeSumAns> res = new ArrayList<>();

        Set<Integer> visitedNum = new HashSet<>();
        for (int i = 0; i < arrays.length; i++) {
            int num = arrays[i];
            if (visitedNum.contains(num)) {
                continue;
            } else {
                int[] subArray = new int[arrays.length - 1];
                if (i > 0) {
                    System.arraycopy(arrays, 0, subArray, 0, i);
                }
                if (i < arrays.length - 1) {
                    System.arraycopy(arrays, i, subArray, i, arrays.length - i - 1);
                }
                List<TwoSumAns> twoRes = findTwoSum(subArray, target - num);
                if (twoRes.isEmpty() == false) {
                    twoRes.stream().forEach(twoSumAns -> {
                        res.add(new ThreeSumAns(num, twoSumAns.getA(), twoSumAns.getB()));
                    });
                }
                visitedNum.add(num);
            }
        }

        return res;
    }

    public static List<TwoSumAns> findTwoSum(int[] arrays, int target) {
        List<TwoSumAns> res = new ArrayList<>();

        int[] copiedArrays = new int[arrays.length];
        System.arraycopy(arrays, 0, copiedArrays, 0, arrays.length);
        QuickSort.quickSort(copiedArrays, 0, copiedArrays.length - 1);
        int left = 0;
        int right = copiedArrays.length - 1;
        int preLeft;
        int preRight;
        while (left < right) {
            int sum = copiedArrays[left] + copiedArrays[right];
            if (sum == target) {
                preLeft = copiedArrays[left];
                preRight = copiedArrays[right];
                res.add(new TwoSumAns(preLeft, preRight));
                while (left < right && copiedArrays[left] == preLeft) {
                    left++;
                }
                while (left < right && copiedArrays[right] == preRight) {
                    right--;
                }
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return res;
    }


    public static class TwoSumAns {
        private final int a;
        private final int b;

        public TwoSumAns(
            int a,
            int b
        ) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    public static class ThreeSumAns {
        private final int a;
        private final int b;
        private final int c;

        public ThreeSumAns(
            int a,
            int b,
            int c
        ) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }
    }

}
