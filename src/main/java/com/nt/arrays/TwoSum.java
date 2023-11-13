package com.nt.arrays;

import java.util.HashMap;

/**
 * @author Enzo
 * @date : 2023/5/31
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 方法一 暴力法 穷举所有两数之和

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int i1 = i + 1; i1 < n; i1++) {

                if (target == nums[i] + nums[i1]) {
                    return new int[]{i, i1};
                }
            }

        }
        return new int[]{};
    }

    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            if (map.containsKey(i1)) {
                return new int[]{map.get(i1), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
