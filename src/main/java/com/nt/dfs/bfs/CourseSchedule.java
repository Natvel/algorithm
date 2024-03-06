package com.nt.dfs.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Enzo
 * @date : 2024/3/5
 */
public class CourseSchedule {
    /**
     * 方法一 BFS
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 定义数组保存所有节点入度
        int[] inDegrees = new int[numCourses];
        // 定义HashMap 存储邻接矩阵
        HashMap<Integer, ArrayList<Integer>> followUpCourses = new HashMap<>();

        // 1. 遍历先决条件 计算入度和后续节点
        for (int[] prerequisite : prerequisites) {

            // 后续课程入度加1
            inDegrees[prerequisite[0]]++;
            // 获取先修课程的后续节点列表
            ArrayList<Integer> followUpCourseList = followUpCourses.getOrDefault(prerequisite[1], new ArrayList<>());

            followUpCourseList.add(prerequisite[0]);
            followUpCourses.put(prerequisite[1], followUpCourseList);
        }
        // 定义队列保存当前可以学习的课程 入度为0的课程
        LinkedList<Integer> selectableCourses = new LinkedList<>();

        // 2. 启动BFS 将入度为0的所有课程入队
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                selectableCourses.offer(i);
            }

        }
        // 3. 不停的出队 将后续课程入度减一 并将新的入度为0 的课程入队
        int finishCoursesNum = 0;
        while (!selectableCourses.isEmpty()) {
            int course = selectableCourses.poll();
            finishCoursesNum++;

            // 遍历当前课程的后续课程 入度减一
            for (Integer followUpCourse : followUpCourses.getOrDefault(course, new ArrayList<>())) {
                inDegrees[followUpCourse]--;
                if (inDegrees[followUpCourse] == 0) {
                    selectableCourses.offer(followUpCourse);
                }
            }
        }
        // 4.判断是否完成所有课程
        return finishCoursesNum == numCourses;
    }

    /**
     * 方法二 DFS
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        // 定义HashMap 存储邻接矩阵
        HashMap<Integer, ArrayList<Integer>> followUpCourses = new HashMap<>();

        // 1. 遍历先决条件 计算入度和后续节点
        for (int[] prerequisite : prerequisites) {

            // 获取先修课程的后续节点列表
            ArrayList<Integer> followUpCourseList = followUpCourses.getOrDefault(prerequisite[1], new ArrayList<>());

            followUpCourseList.add(prerequisite[0]);
            followUpCourses.put(prerequisite[1], followUpCourseList);
        }

        // 定义一个栈优先搜索最后要学习的课程
        Stack<Integer> lastCourse = new Stack<>();
        // 定义一个数组 保存课程是否在当前搜索路劲上出现过
        boolean[] isSearched = new boolean[numCourses];

        boolean canFinish = true;

        // 2. 遍历每一个节点进行DFS
        for (int i = 0; i < numCourses && canFinish; i++) {
            if (!lastCourse.contains(i)) {
                // 不在栈内就搜索
                canFinish = dfs(followUpCourses, lastCourse, isSearched, i);
            }
        }
        return canFinish;
    }

    /**
     * 实现辅助DFS方法
     *
     * @param followUpCourses
     * @param lastCourses
     * @param isSearched
     * @return
     */
    public boolean dfs(HashMap<Integer, ArrayList<Integer>> followUpCourses, Stack<Integer> lastCourses, boolean[] isSearched, int i) {

        // 当前节点在路径中出现
        isSearched[i] = true;
        for (Integer followCourse : followUpCourses.getOrDefault(i, new ArrayList<>())) {
            if (isSearched[followCourse]) {
                return false;
            } else {
                if (!dfs(followUpCourses, lastCourses, isSearched, followCourse))
                    return false;
            }
        }
        // 后续节点处理完毕 当前节点入度
        lastCourses.push(i);
        isSearched[i] = false;

        return true;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}};
        int[][] prerequisites2 = {{1, 0}, {0, 1}};

        CourseSchedule courseSchedule = new CourseSchedule();

        System.out.println(courseSchedule.canFinish(2, prerequisites));
        System.out.println(courseSchedule.canFinish(2, prerequisites2));
    }

}
