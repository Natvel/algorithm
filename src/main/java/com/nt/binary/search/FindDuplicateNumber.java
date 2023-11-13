package com.nt.binary.search;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Enzo
 * @date : 2023/6/28
 */
public class FindDuplicateNumber {

    /**
     * 方法一 使用hash保存每个数出现的次数
     */

    public int findDuplicate(int[] nums) {
        Map<Integer, Integer> hashMap = Maps.newHashMap();

        // 遍历所有元素,统计count值
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                return num;
            }
            hashMap.put(num, 0);
        }
        return -1;
    }

    public int findDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }

        }
        return -1;
    }


    /**
     * 二分查找,查找1-N的自然数序列,寻找target
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        // 定义左右指针
        int left = 1;
        int right = nums.length - 1;

        while (left <= right) {
            // 计算中间值
            int mid = (left + right) / 2;
            // 对当前的mid计算count值
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            // 判断count和mid本身大小关系
            if (count <= mid) {
                // count小于等于mid自身,说明mid比target小,左指针右移
                left = mid + 1;
            } else {
                right = mid;
            }
            // 左右指针重合时,找到target
            if (left == right) {
                return left;
            }
        }
        return -1;
    }

    /***
     * 快慢指针
     * @param nums
     * @return
     */
    public int findDuplicate3(int[] nums) {
        // 定义快慢指针
        int fast = 0, slow = 0;
        // 1.寻找环内的相遇点

        do {
            // 快指针一次走两步,慢指针一次走一步
            slow = nums[slow];
            fast = nums[nums[fast]];

        } while (fast != slow);

        // 循环结束 slow和fast相等,都是相遇点
        // 2. 寻找环的入口点
        // 另外定义两个指针,固定间距
        int before = 0;
        int after = slow;
        while (before != after) {
            before = nums[before];
            after = nums[after];
        }
        return after;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        int[] nums2 = {1, 1};
        FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
        System.out.println(findDuplicateNumber.findDuplicate3(nums));
        System.out.println(findDuplicateNumber.findDuplicate3(nums2));

    }


}
