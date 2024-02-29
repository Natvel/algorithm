package com.nt.greedy;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Enzo
 * @date : 2024/2/26
 */
public class TaskScheduler {

    /**
     * 模拟法
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterVal(char[] tasks, int n) {
        // 用HashMap统计每个任务出现的次数
        Map<Character, Integer> hashMap = Maps.newHashMap();
        for (char task : tasks) {
            hashMap.put(task, hashMap.getOrDefault(task, 0) + 1);

        }
        int t = hashMap.size();
        // 定义两个状态列表 任务剩余次数
        List<Integer> restCont = new ArrayList<>(hashMap.values());
        List<Integer> nextAvailableTime = Lists.newArrayList(Collections.nCopies(t, 1));

        // 模拟CPU时钟
        int time = 0;
        for (int i = 0; i < tasks.length; i++) {
            time++;
            int minNextAvailableTime = Integer.MAX_VALUE;
            // 1. 获取所有任务中最早可执行的时间
            for (int j = 0; j < t; j++) {
                // 取还没做完的任务
                if (restCont.get(j) != 0) {
                    minNextAvailableTime = Math.min(minNextAvailableTime, nextAvailableTime.get(j));
                }
            }

            // 2. 直接推进时间 执行任务
            time = Math.max(time, minNextAvailableTime);
            // 3. 选取可执行任务中 剩余次数最多的那个执行
            int maxRestCountTask = -1;
            for (int j = 0; j < t; j++) {
                if (restCont.get(j) > 0 && nextAvailableTime.get(j) <= time) {
                    if (maxRestCountTask == -1 || restCont.get(j) > restCont.get(maxRestCountTask)){
                        maxRestCountTask = j;
                    }
                }
            }
            nextAvailableTime.set(maxRestCountTask, time + n + 1);
            restCont.set(maxRestCountTask, restCont.get(maxRestCountTask) - 1);
        }
        return time;
    }

    public int leastInterVal1(char[] tasks, int n) {
        Map<Character, Integer> hashMap = Maps.newHashMap();
        for (char task : tasks) {
            hashMap.put(task, hashMap.getOrDefault(task, 0) + 1);

        }
        int t = hashMap.size();
        int maxCount = 0;
        int maxNum = 0;
        // 计算maxCount
        for (Integer count : hashMap.values()) {
            maxCount = Math.max(maxCount, count);
        }
        // 2. 计算maxNum
        for (Character task : hashMap.keySet()) {
            if (hashMap.get(task) == maxCount){
                maxNum++;
            }
        }
        return Math.max(tasks.length, (maxCount - 1) * (n + 1) + maxNum);
    }
    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        TaskScheduler taskScheduler = new TaskScheduler();

        System.out.println(taskScheduler.leastInterVal(tasks, n));
    }
}
