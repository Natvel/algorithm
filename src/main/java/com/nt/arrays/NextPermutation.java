package com.nt.arrays;

import java.util.Arrays;

/**
 * @author Enzo
 * @date : 2023/6/14
 */
public class NextPermutation {

    // 思路 从后往前找到升序子序列 然后调整子序列的最高位 剩余部分升序排序
    public void nextPermutation(int[] nums) {

        int n = nums.length;
        // 1. 从后往前找到升序子序列,找到一次下降的数 位置记为k
        int k = n - 2;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }
        // 找到k 就是需要调整部分的最高位

        // 2. 如果k = -1,说明所有数降序排列 改成升序排列
        if (k == -1) {
            Arrays.sort(nums);
            return;
        }
        // 3.一般情况 k>=0
        // 3.1 依次遍历剩余降序排列的部分 找到要替换的最高位的那个数

        int i = k + 2;
        while (i < n && nums[i] > nums[k]) {
            // 当前的i就是后面部分第一个比num[k]小的数,i-1就是要替换的那个数
            i++;
        }

        int temp = nums[k];
        nums[k] = nums[i - 1];
        nums[i - 1] = temp;

        // 3.3
        int start = k + 1;
        int end = n - 1;

        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            end--;
        }

    }


    // 思路 从后往前找到升序子序列 然后调整子序列的最高位 剩余部分升序排序
    public void nextPermutation2(int[] nums) {

        int n = nums.length;
        // 1. 从后往前找到升序子序列,找到一次下降的数 位置记为k
        int k = n - 2;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }
        // 找到k 就是需要调整部分的最高位

        // 2. 如果k = -1,说明所有数降序排列 改成升序排列
        if (k == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        // 3.一般情况 k>=0
        // 3.1 依次遍历剩余降序排列的部分 找到要替换的最高位的那个数

        int i = k + 2;
        while (i < n && nums[i] > nums[k]) {
            // 当前的i就是后面部分第一个比num[k]小的数,i-1就是要替换的那个数
            i++;
        }

        swap(nums, k, i - 1);

        // 3.3
        reverse(nums, k + 1, n - 1);

    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};

        NextPermutation permutation = new NextPermutation();
        permutation.nextPermutation2( nums);

        for (int num : nums) {
            System.out.print(num + "\t");
        }

    }
}
