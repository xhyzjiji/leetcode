package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.util.Arrays;
import java.util.Comparator;

public class Q2_3_15 {

    public static class NutBoltBase {
        private int val;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    // 螺帽螺钉问题
    public static class Nut extends NutBoltBase {
        @Override
        public String toString() {
            return "Nut" + getVal();
        }

        public Nut(int val) {
            setVal(val);
        }
    }

    public static class Bolt extends NutBoltBase {
        @Override
        public String toString() {
            return "Bolt" + getVal();
        }

        public Bolt(int val) {
            setVal(val);
        }
    }

    public enum NutBoltComRes {
        IMCOMPARABLE,
        EQUAL,
        LESS,
        GREAT
    }
    public static class NutBoltComparator {
        public static NutBoltComRes compare(NutBoltBase o1, NutBoltBase o2) throws NullPointerException {
            if (o1 == null || o2 == null) {
                throw new NullPointerException("npe");
            }

            if (o1 == o2) {
                return NutBoltComRes.EQUAL;
            }

            boolean o1IsNut = o1 instanceof Nut;
            boolean o2IsNut = o2 instanceof Nut;
            if (o1IsNut != o2IsNut) {
                if (o1.val == o2.val) {
                    return NutBoltComRes.EQUAL;
                } else if (o1.val > o2.val) {
                    return NutBoltComRes.GREAT;
                } else {
                    return NutBoltComRes.LESS;
                }
            } else {
                return NutBoltComRes.IMCOMPARABLE;
            }
        }
    }

    private static void swap(Object[] a, int l, int r) {
        Object temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }

    public static void sort(NutBoltBase[] a) {
        int mid = classify(a);
        subSort(a, 0, mid, mid+1);
    }

    public static void subSort(NutBoltBase[] a, int low, int high, int offset) {
        if (low >= high) {
            return;
        }

        System.out.println(Arrays.toString(a));
        int req = partition(a, low, low+offset, high+offset);
        System.out.println(Arrays.toString(a));
        int leq = partition(a, req, low, high);
        subSort(a, low, leq-1, offset);
        subSort(a, leq+1, high, offset);
    }

    public static int partition(NutBoltBase[] a, int v, int low, int high) {
        if (low >= high) {
            return -1;
        }

        NutBoltBase vv = a[v];
        int i = low;
        int gt = high;
        int eq = -1;
        while (i <= gt) {
            NutBoltComRes comRes = NutBoltComparator.compare(a[i], vv);
            if (comRes == NutBoltComRes.LESS) {
                if (eq >= 0 && eq < i) {
                    swap(a, i, eq);
                    eq = i;
                }
                i++;
            } else if (comRes == NutBoltComRes.GREAT) {
                swap(a, i, gt);
                gt--;
            } else if (comRes == NutBoltComRes.EQUAL) {
                eq = i;
                i++;
            } else {
                throw new RuntimeException("incomparable in the same type");
            }
        }
        return eq;
    }

    // a[0, mid], a[mid+1, len(a)-1]
    public static int classify(NutBoltBase[] a) {
        int low = 0;
        int high = a.length - 1;
        while(low < high) {
            if (a[low] instanceof Nut) {
                low++;
            } else {
                swap(a, low, high);
                high--;
            }
        }

        return low-1;
    }

    public static void main(String[] args) {
        NutBoltBase[] a = new NutBoltBase[]{
            new Nut(4),
            new Bolt(3),
            new Nut(2),
            new Bolt(4),
            new Nut(3),
            new Bolt(1),
            new Nut(5),
            new Bolt(2),
            new Nut(1),
            new Bolt(5)
        };
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
