package com.nt.sliding.window;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Enzo
 * @date : 2023/7/20
 */
public class SlidingWindowMaximum {


    /**
     * 方法一: 暴力法，遍历每一个窗口,对每个窗口遍历每个元素求最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        // 定义一个结果数组,总共有n-k+1个窗口
        int[] result = new int[nums.length - k + 1];
        // 遍历数组,从0到n-k,作为滑动窗口的起始位置
        for (int i = 0; i < nums.length - k; i++) {
            // 找窗口内的最大值,定义一个变量来保存
            int max = nums[i];
            // 遍历窗口中的每一个元素,比较大小
            for (int j = i + 1; j < i + k; j++) {

                if (nums[j] > max) {
                    max = nums[j];
                }
                result[i] = max;
            }
        }
        return result;
    }

    /**
     * 方法二: 使用大顶堆
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {

        // 定义一个结果数组
        int[] result = new int[nums.length - k +1];


        // 用优先队列实现一个大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);


        // 准备工作,构建大顶堆,将第一个窗口元素前k个放入堆中

        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }
        // 当前大顶堆的堆顶元素就是第一个窗口的最大值
        result[0] = maxHeap.peek();

        // 遍历所有窗口
        for (int i = 1; i <= nums.length - k; i++) {
            // 删除堆中上一个窗口的元素
            maxHeap.remove(nums[i - 1]);
            // 添加当前窗口的最后一个元素进堆
            maxHeap.add(nums[i + k - 1]);
            result[i] = maxHeap.peek();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        SlidingWindowMaximum maximum = new SlidingWindowMaximum();
        int[] ints = maximum.maxSlidingWindow2(input, k);
        for (int anInt : ints) {
            System.out.print(anInt + "\t");
        }
    }

}
