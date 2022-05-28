package com.meituan.xhyzjiji.number.offer.qisimiaoxiang;

public class Pow {

    public static int internalPow(int base, int exp) {
        if (exp == 0) {
            return 1;
        }
        int temp = internalPow(base, exp >>> 1);
        int res = temp * temp;
        if ((exp & 0x01) == 1) {
            res *= base;
        }
        return res;
    }

    public static double pow(int base, int exp) {
        if (exp < 0) {
            int res = internalPow(base, -exp);
            return 1D/res;
        } else {
            return internalPow(base, exp);
        }
    }

    public static void main(String[] args) {
        System.out.println(pow(2, 30));
        System.out.println(pow(2, -30));
    }

}
