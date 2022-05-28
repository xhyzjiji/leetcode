package com.meituan.xhyzjiji.number.offer.qisimiaoxiang;

public class Ceil {

    private static int ceilSameSign(int a, int b) {
        return (a + b - 1) / b;
    }

    public static int ceil(int a, int b) {
        if ((a > 0 && b > 0) ||
            (a < 0 && b < 0)) {
            return ceilSameSign(a, b);
        } else {
            return -ceilSameSign(-a, b);
        }
    }

    public static void main(String[] args) {
        System.out.println("10 / 3 = " + ceil(10, 3));
        System.out.println("-10 / 3 = " + ceil(-10, 3));
    }

}
