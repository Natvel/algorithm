package com.nt.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Enzo
 * @date : 2024/2/21
 */
public class KthLargestElement {
    /**
     * 方法一直接排序
     */
    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 方法二基于快排的选择
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int start, int end, int index) {
        // 找到pivot的位置返回
        int position = randomPartition(nums, start, end);
        // 判断当前pivot位置是否为index

        if (position == index) {
            return nums[position];
        } else {
            return position > index ? quickSelect(nums, start, position - 1, index) : quickSelect(nums, start, position + 1, index);
        }
    }

    /**
     * 实现一个随机分区方法
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int randomPartition(int[] nums,int start,int end){
        Random random = new Random();
        int randIndex = start + random.nextInt(end - start + 1);
        QuickSort.swap(nums,start,randIndex);
        return QuickSort.partition(nums, start, end);
    }

    /**
     * 方法三 基于堆排序的选择
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest3(int[] nums, int k) {

        int n = nums.length;

        // 保存堆的大小初始就是n
        int heapSize = n;
        // 1.构建大顶堆
        buildMaxHeap(nums,heapSize);
        // 2. 执行k-1次删除堆顶元素操作
        for (int i = n - 1; i > n - k; i--) {
            // 将堆顶元素交换到当前堆的末尾
            QuickSort.swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }
        // 3.返回当前堆顶元素
        return nums[0];
    }

    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }

    }

    private void maxHeapify(int[] nums, int top, int heapSize) {
        // 定义左右子节点
        int left = top >> 1 + 1;
        int right = top >> 1 + 2;
        // 保存当前最大元素的索引位置
        int largest = top;
        // 比较左右子节点 记录最大元素索引位置
        if (right < heapSize && nums[right] > nums[largest]){
            largest = right;
        }
        if (left < heapSize && nums[left] > nums[largest]){
            largest = left;
        }

        // 将最大元素换到堆顶
        if (largest != top){
            QuickSort.swap(nums, top, largest);
            //
            maxHeapify(nums, largest, heapSize);
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        KthLargestElement kthLargestElement = new KthLargestElement();
        System.out.println(kthLargestElement.findKthLargest1(nums1, 2));
        System.out.println(kthLargestElement.findKthLargest1(nums2, 4));
    }
}
