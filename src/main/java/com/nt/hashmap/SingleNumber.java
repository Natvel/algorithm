package com.nt.hashmap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * @author Enzo
 * @date : 2024/2/18
 */
public class SingleNumber {
    /**
     * 暴力法
     *
     * @return
     */
    public int singleNumber1(int[] nums) {
        // 定义一个列表 保存当前所有出现过一次的元素
        List<Integer> list = Lists.newArrayList();

        // 遍历所有元素
        for (Integer num : nums) {

            if (list.contains(num)) {
                // 如果已经出现过 删除列表中的元素
                list.remove(num);
            } else {
                // 没有出现过 直接保存
                list.add(num);
            }
        }
        return list.get(0);
    }

    /**
     * 保存元素到hashmap
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {

        Map<Integer, Integer> hashMap = Maps.newHashMap();
        for (int num : nums) {
            if (hashMap.get(num) != null) {
                hashMap.remove(num);
            } else {
                hashMap.put(num, 1);
            }
        }
        return hashMap.keySet().iterator().next();
    }

    /**
     * 方法三 用set去重 a = 2 * (a + b + c)  - (a + b + c + b + c)
     *
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {

        // 定义一个hashSet进行去重
        Set<Integer> hashSet = Sets.newHashSet();

        int arrayNum = 0;
        int setNum = 0;
        // 1. 遍历数组元素 保存到set并进行求和
        for (int num : nums) {
            hashSet.add(num);
            arrayNum += num;
        }
        for (Integer num : hashSet) {
            setNum += num;
        }
        return setNum >> 1 - arrayNum;
    }

    /**
     * 方法四 数学方法 做异或
     * @param nums
     * @return
     */
    public int singleNumber4  (int[] nums) {

        int result = 0;
        // 遍历所有数据 按位做异或
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        SingleNumber singleNumber = new SingleNumber();
        System.out.println(singleNumber.singleNumber4(nums));
    }
}
