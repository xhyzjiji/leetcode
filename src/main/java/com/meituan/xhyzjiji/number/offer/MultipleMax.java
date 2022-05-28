package com.meituan.xhyzjiji.number.offer;

public class MultipleMax {

    static int[][] a = new int[][]{
        {1,2,-3},
        {2,-3,4},
        {3,4,-5}
    };
    static int rows = a.length;
    static int cols = a[0].length;

    public static void main(String[] args) {
        int[][] dp = new int[rows][cols];
        // F(i,j) = max{F(i-1,j)*a(i,j), F(i,j-1)*a(i,j)}
        System.out.println(internalDP(dp, rows-1, cols-1));
    }

    public static int internalDP(int[][] dp, int r, int c) {
        if (r == 0 && c == 0) {
            return a[0][0];
        }

        int max = Integer.MIN_VALUE;
        if (r > 0) {
            max = internalDP(dp, r-1, c) * a[r][c];
        }
        if (c > 0) {
            int temp = internalDP(dp, r, c-1) * a[r][c];
            max = (temp > max ? temp : max);
        }
        return max;
    }

}
