package com.nt.bit.operator;

/**
 * @author Enzo
 * @date : 2024/3/6
 */
public class HammingDistance {

    /**
     * 计算
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x,int y){
        return Integer.bitCount(x ^ y);
    }

    /**
     * 方法二 自行已实现统计1的个数 逐位右移动
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance2(int x,int y){
        // 保存异或几个
        int xor = x ^ y;
        // 保存当前1的个数
        int count = 0;
        // 逐位右移知道几个为0
        while (xor != 0){
            // 如果最后一位为1 count      ++
            if ((xor & 1) == 1) count++;
            // 右移动一位
            xor >>= 1;
        }
        return count;
    }

    /**
     * 方法三  快速位移
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance3(int x,int y){
        // 保存异或几个
        int xor = x ^ y;
        // 保存当前1的个数
        int count = 0;
        // 快速位移 每次寻找当前最右面的一个1 直接消去
        while (xor != 0){
            xor &= xor - 1;
            count++;
        }
        return count;
    }
    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(1, 4));
    }
}
