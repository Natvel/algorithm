package com.nt.greedy;

/**
 * @author Enzo
 * @date : 2024/2/26
 */
public class JumpGame2 {
    /**
     * 反向跳跃
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        // 定一个变量保存步数
        int steps = 0;

        // 定义循环变量
        int curPosition = nums.length - 1;
        // 不停的向前跳跃 以最远的距离
        while (curPosition > 0) {
            // 从前到后遍历数组元素 找到当前距离最远的上一步位置
            for (int i = 0; i < curPosition; i++) {
                if (i + nums[i] >= curPosition) {
                    // 从前到后 第一次能跳到当前位置 就是上一步最远位置
                    curPosition = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    /**
     * 方法二 正向跳跃 考虑能够跳到最远的两步
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {

         int steps = 0;
        int currPosition = 0;
        // 定义双指针 指向当前位置跳异步和两步分别能到的最远位置
        int farthest = 0;
        int nextFarthest = farthest;

        // 不停贪心寻找下一步的合适位置
        // while (farthest < nums.length - 1) {
        // 遍历currPosition~farthest范围内所有元素 选择第二步跳跃最远的作为当前一步的选择
        for (int i = 0; i < nums.length - 1; i++) {

            // 如果比之前第二步最远距离大 更新
            if (i + nums[i] > nextFarthest) {
                nextFarthest = i + nums[i];
            }
            if (i == farthest) {
                // 当前一步完成
                steps++;
                farthest = nextFarthest;
            }
            //  }
        }
        return ++steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};

        JumpGame2 jumpGameII = new JumpGame2();

        System.out.println(jumpGameII.jump(nums));
    }
}
