package com.nt.stack.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Enzo
 * @date : 2024/2/19
 * 使用一个队列实现自定义栈
 */
public class MyStack2 {

    Queue<Integer> queue;

    public MyStack2() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        // 1. 当前记录队列长度
        int l = queue.size();
        // 2. 把x入队
        queue.offer(x);
        // 3. 把queue中原先的所有元素依次出队 然后再入队
        for (int i = 0; i < l; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public int top() {
        return queue.peek();
    }

    public boolean empty(){
        return queue.isEmpty();
    }
}
