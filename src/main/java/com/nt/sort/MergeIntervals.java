package com.nt.sort;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Enzo
 * @date : 2024/2/21
 */
public class MergeIntervals {

    /**
     * 按区间左边界排序
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {

        // 定义一个结果数组
        List<int[]> list = Lists.newArrayList();
        // 将所有区间按照左边界排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        //2. 遍历排序后的区间 逐个合并
        for (int[] interval : intervals) {

            // 记录当前的左右边界
            int left = interval[0], right = interval[1];
            // 获取结果数组长度
            int length = list.size();

            // 如果left比最后一个区间的右边界大 不能合并 直接添加到结果
            if (length == 0 || left > list.get(length - 1)[1]) {
                list.add(interval);
            } else {
                // 可以合并
                int mergeLeft = list.get(length - 1)[0];
                int mergeRight = Math.max(list.get(length - 1)[1], right);
                list.set(length - 1, new int[]{mergeLeft, mergeRight});
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        MergeIntervals mergeIntervals = new MergeIntervals();

        for (int[] interval : mergeIntervals.merge(intervals)) {
            QuickSort.printArray(interval);
        }
    }
}
