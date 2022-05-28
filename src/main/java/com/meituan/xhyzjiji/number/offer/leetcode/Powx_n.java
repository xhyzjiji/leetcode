package com.meituan.xhyzjiji.number.offer.leetcode;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Powx_n {

    public double internalPow1(double x, int n) {
        // 位运算，如 n = 5，二进制为 101，则 x^5 = x^4 * x;
        double res = 1.0;
        double mul = x;
        while (n > 0) {
            if ((n & 0x01) == 1) {
                res *= mul;
            }
            mul *= mul;
            n >>>= 1;
        }
        return res;
    }
    public double myPow1(double x, int n) {
        if (n > 0) {
            return internalPow1(x, n);
        } else {
            return 1 / internalPow1(x, 0 - n);
        }
    }

    public double internalPow2(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        double temp = internalPow2(x, n >> 1);
        if ((n & 0x01) == 0) {
            return temp * temp;
        } else {
            if (n > 0) {
                return x * temp * temp;
            } else {
                return temp / x;
            }
        }
    }
    public double myPow2(double x, int n) {
        if (n > 0) {
            return internalPow2(x, n);
        } else {
            return 1 / internalPow2(x, 0 - n);
        }
    }

    public static void main(String[] args) {
        Powx_n powx_n = new Powx_n();
        System.out.println(powx_n.myPow1(8.84372, -5));
    }

}
