package com.nt.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Enzo
 * @date : 2024/2/19
 */
public class MyStack {

    // 定义两个队列
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /**
     * 入栈方法
     */

    public void push(int x) {
        // 1.把x保存到queue2中
        queue2.offer(x);
        // 2. 将queue中所有元素依次出队然后放入queue2
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        // 交换两个队列
        Queue<Integer> temp = this.queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /**
     * 出栈操作
     */
    public int pop() {
        return queue1.poll();
    }

    /**
     * 判断为空
     */
    public boolean empty() {
        return queue1.isEmpty();
    }
}
