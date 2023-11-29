package com.nt.arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Enzo
 * @date : 2023/11/13
 */
public class TwoSum {
    /**
     * 方式一 暴力法
     */
    public int[] twoSum(int[] nums, int target) {
        // 双重for循环
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /** jiao
     * hash
     *`
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        // 遍历数组，将数据全部保存hash表
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[]{};
    }


    public static void main(String[] args) {
        int[] input = {3, 2, 4};
        int target = 6;
        TwoSum twoSum = new TwoSum();
        int[] lists = twoSum.twoSum2(input, target);
        for (int i = 0; i < lists.length; i++) {
            System.out.print(lists[i]);
        }
    }

}
