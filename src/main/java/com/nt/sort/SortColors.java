package com.nt.sort;

import java.util.Arrays;

/**
 * @author Enzo
 * @date : 2024/2/21
 */
public class SortColors {

    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }

    /**
     * 方法二 基于选择排序
     *
     * @param nums
     */
    public void sortColors1(int[] nums) {
        // 定义一个指针 指向当前应该填入元素的位置
        int curr = 0;

        // 1. 遍历数组 将所有0交换到数组头部
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                QuickSort.swap(nums, curr++, i);
            }
        }

        // 1. 遍历数组 将所有0交换到数组头部
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                QuickSort.swap(nums, curr++, i);
            }
        }
    }

    /**
     * 方法三 基于计数排序的过程
     *
     * @param nums
     */

    public void sortColors2(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            }
            // 将0，1，2按照个数依次填入nums数组
            for (int i = 0; i < nums.length; i++) {
                if (i < count0) {
                    nums[i] = 0;
                } else if (i < count0 + count1) {
                    nums[i] = 1;
                } else {
                    nums[i] = 2;
                }
            }
        }
    }

    /**
     * 基于快速排序
     *
     * @param nums
     */
    public void sortColors3(int[] nums) {
        // 定义左右指针
        int left = 0, right = nums.length - 1;
        // 定义一个遍历所有元素的指针
        int i = left;

        // 循环判断 遍历元素
        while (i <= right) {
            // 1. 如果是2 换到末尾 右指针左移
            while (i <= right && nums[i] == 2){
                QuickSort.swap(nums, i, right--);
            }
            if (nums[i] == 0){
                QuickSort.swap(nums, i, left++);
            }
            // 3. i++
            i++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(nums);

        QuickSort.printArray(nums);
    }
}
