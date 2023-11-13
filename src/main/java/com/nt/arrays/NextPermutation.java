package com.nt.arrays;

/**
 * @author Enzo
 * @date : 2023/6/14
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {

        int length = nums.length;


        // 1. 从后往前找到盛旭子序列,找到第一次下降的数,位置为k
        int k = length - 2;


        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }
        // 找到k, 就是需要调整部分的最高位
        // 2. 如果k = -1，说明所有数降序排列,改成升序
        if (k == -1) {
            reverse(nums, 0, length - 1);
            return;
        }
        // 3. 一般情况， k>=0
        // 3.1 依次遍历剩余降序排列的部分,找到要替换最高位的那个数
        int i = k + 2;
        while (i < length && nums[i] > nums[k]) {
            // 当前的i,就是后面部分第一个比num[k]小的数,i-1就是要替换的那个数
            i++;
        }
        // 3.2 交换i-1和k位置上的数

        swap(nums, k, i - 1);

        // 3.3 k之后的的剩余部分变成升序排列,直接前后调换
        reverse(nums, k + 1, length - 1);
    }

    // 定义一个方法交换数组中两个元素
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

        int[] nums = {1, 3, 2};

        NextPermutation permutation = new NextPermutation();
        permutation.nextPermutation(nums);

        for (int num : nums) {
            System.out.print(num + "\t");
        }

    }
}
