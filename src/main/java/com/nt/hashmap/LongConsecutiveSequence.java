package com.nt.hashmap;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author Enzo
 * @date : 2024/2/18
 */
public class LongConsecutiveSequence {

    /**
     * 方法一 暴力法
     *
     * @param nums
     * @return
     */
    public int longestConsecutiveSequence(int[] nums) {
        // 定义一个变量 保存当前最长连续序列的长度
        int maxLength = 0;
        for (int num : nums) {
            // 保存当前元素作为起点
            int currNum = num;
            int currLength = 1;
            while (contains(nums, currNum + 1)) {
                currLength++;
                currNum++;
            }
            // 判断当前连续序列长度是否为最大
            maxLength = Math.max(currLength, maxLength);
        }
        return maxLength;
    }

    /**
     * 利用hash表改进
     *
     * @param nums
     * @return
     */
    public int longestConsecutiveSequence2(int[] nums) {
        // 定义一个变量 保存当前最长连续序列的长度
        int maxLength = 0;
        // 定义一个hashSet 保存所有出现的数值
        Set<Integer> hashSet = Sets.newHashSet();

        for (int num : nums) {
            hashSet.add(num);
        }
        for (int num : nums) {

            // 保存当前元素作为起始点
            int currNum = num;
            // 保存当前连续序列长度
            int currLength = 1;
            // 寻找后续数字 组成连续序列
            while (hashSet.contains(currNum + 1)) {
                currLength++;
                currNum++;
            }
            // 判断当前连续序列长度是否为最大
            maxLength = Math.max(currLength, maxLength);
        }
        return maxLength;
    }

    /**
     * 利用hash表改进
     *
     * @param nums
     * @return
     */
    public int longestConsecutiveSequence3(int[] nums) {
        // 定义一个变量 保存当前最长连续序列的长度
        int maxLength = 0;
        // 定义一个hashSet 保存所有出现的数值
        Set<Integer> hashSet = Sets.newHashSet();
        for (int num : nums) {
            hashSet.add(num);
        }
        for (int num : nums) {
            // 保存当前元素作为起始点
            int currNum = num;
            // 保存当前连续序列长度
            int currLength = 1;
            if (!hashSet.contains(currNum - 1)) {
                // 寻找后续数字 组成连续序列
                while (hashSet.contains(currNum + 1)) {
                    currLength++;
                    currNum++;
                }
                // 判断当前连续序列长度是否为最大
                maxLength = Math.max(currLength, maxLength);
            }
        }
        return maxLength;
    }

    private boolean contains(int[] nums, int x) {
        for (int num : nums) {
            if (num == x) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        LongConsecutiveSequence longestConsecutiveSequence = new LongConsecutiveSequence();
        System.out.println(longestConsecutiveSequence.longestConsecutiveSequence3(nums1));
        System.out.println(longestConsecutiveSequence.longestConsecutiveSequence3(nums2));
    }

}

