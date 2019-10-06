package com.meituan.xhyzjiji.number.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZTrans {

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     *
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     * 示例 1:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     *
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    private static final Logger log = LoggerFactory.getLogger(ZTrans.class);

    public static void main(String[] args) {
        ZTrans solution = new ZTrans();

        String tc = "LEETCODEISHIRING";
        log.info("input={}, numRows={}, ans={}", tc, 1, solution.convert(tc, 1));
        log.info("input={}, numRows={}, ans={}", tc, 2, solution.convert(tc, 2));
        log.info("input={}, numRows={}, ans={}", tc, 3, solution.convert(tc, 3));
        log.info("input={}, numRows={}, ans={}", tc, 4, solution.convert(tc, 4));

        String tc1 = "PAYPALISHIRING";
        log.info("input={}, numRows={}, ans={}", tc1, 4, solution.convert(tc1, 4));
    }

    // 5ms, 37MB
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }

        byte[] originByteArray = s.getBytes();
        byte[] res = new byte[originByteArray.length];
        int resIndex = 0;
        for (int i = 0; i < numRows; i++) {
            if (i == 0 || i == numRows - 1) {
                int tempIndex = i;
                while (tempIndex < originByteArray.length) {
                    res[resIndex] = originByteArray[tempIndex];
                    tempIndex += numRows + numRows - 2;
                    resIndex++;
                }
            } else {
                int tempIndex = i;
                boolean isMiddle = false;
                while (tempIndex < originByteArray.length) {
                    if (isMiddle) {
                        res[resIndex] = originByteArray[tempIndex];
                        resIndex++;
                        tempIndex += i + i;
                        isMiddle = false;
                    } else {
                        res[resIndex] = originByteArray[tempIndex];
                        resIndex++;
                        tempIndex += numRows - i + numRows - 2 - i;
                        isMiddle = true;
                    }
                }
            }
        }

        return new String(res);
    }

}
