package com.nt.arrays;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.Var;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.*;

/**
 * @author Enzo
 * @date : 2023/6/9
 */
public class ThreeSum {

    /**
     * 方法一
     */
    public List<List<Integer>> threeSum(int[] nums) {

        int n = nums.length;


        List<List<Integer>> list = Lists.newArrayList();

        // 三重for循环
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return list;
    }

    /**
     * 只用hash表保存结果
     *
     * @param nums
     */
    public List<List<Integer>> threeSum2(int[] nums) {

        int n = nums.length;
        List<List<Integer>> result = Lists.newArrayList();

        // 定义一个hashmap
        Map<Integer, List<Integer>> map = Maps.newHashMap();

        // 遍历数组,寻找每个对应的数值
        for (int i = 0; i < n; i++) {
            int thatNum = 0 - nums[i];
            if (map.containsKey(thatNum)) {
                // 如果已近存在thatNum,就找到一组解
                ArrayList<Integer> tempList = Lists.newArrayList(map.get(thatNum));
                tempList.add(nums[i]);
                result.add(tempList);
            }

            for (int j = 0; j < i; j++) {
                // 以两数之和等于key
                int newKey = nums[i] + nums[j];
                // 如果可以不存在,就直接添加进去
                if (!map.containsKey(newKey)) {
                    ArrayList<Integer> list = Lists.newArrayList();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    map.put(newKey, list);
                }
            }
        }
        return result;
    }

    // 方法三 双指针发
    public List<List<Integer>> threeSum3(int[] nums) {
        int length = nums.length;
        List<List<Integer>> list = Lists.newArrayList();
        // 0. 先对数组排序
        Arrays.sort(nums);
        // 1.遍历每一个元素 作为当前三元组中最小的的那个
        for (int i = 0; i < length; i++) {
            // 1.1 如果当前数已经大于0 直接退出循环
            if (nums[i] > 0) {
                break;
            }
            // 1.2 如果当前数据已经出现过 直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 1.3 常规情况 以当前数量最小数 定义左右指针
            int lp = i + 1;
            int rp = length - 1;
            // 只要左右指针不重叠 就继续移动指针
            while (lp < rp) {
                int sum = nums[i] + nums[lp] + nums[rp];
                // 判读sum 与0做大小对比
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[lp], nums[rp]));
                    lp++;
                    rp--;
                    // 如果移动之后的元素相同 直接跳过
                    while (lp < rp && nums[lp] == nums[lp + 1]) {
                        lp++;
                    }
                    while (lp < rp && nums[rp] == nums[rp - 1]) {
                        rp--;
                    }
                    // 1.3.2小于0 较小的数增大 左指针右移
                } else if (sum < 0) {
                    lp++;
                } else {
                    rp--;
                }
            }

        }
        return list;
    }

    public static void main(String[] args) {

        int[] input = {-1, 0, 1, 2, -1, -4};

        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum3(input));
    }

}
