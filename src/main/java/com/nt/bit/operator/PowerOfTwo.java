package com.nt.bit.operator;

/**
 * @author Enzo
 * @date : 2024/3/6
 */
public class PowerOfTwo {
    /**
     * 方法一
     *
     * @param n
     * @return
     */
    public Boolean isPowerOfTwo(int n) {
        // 处理特殊情况
        if (n <= 0) return false;

        // 不停的除以二 直到结果为奇数
        while (n % 2 == 0) {
            n /= 2;
        }
        // 如果最终n变成了1 就是2的整次
        return n == 1;
    }

    /**
     * 方法二 位运算 和自身减一做位与
     *
     * @param n
     * @return
     */
    public Boolean isPowerOfTwo1(int n) {
        // 处理特殊情况
        if (n <= 0) return false;
        return (n & n - 1) == 0;
    }


    /**
     * 方法二 位运算 和自身减一做位与
     *
     * @param n
     * @return
     */
    public Boolean isPowerOfTwo2(int n) {
        // 处理特殊情况
        if (n <= 0) return false;
        return (n & -n) == n;
    }

    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo(218));
        System.out.println(powerOfTwo.isPowerOfTwo(128));
    }
}
