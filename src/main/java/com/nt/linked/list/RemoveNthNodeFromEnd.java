package com.nt.linked.list;

import java.util.Stack;

/**
 * @author Enzo
 * @date : 2024/1/23
 */
public class RemoveNthNodeFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. 遍历链表 得到长度l

        int length = getLength(head);

        // 2. 从前到后继续遍历 找到正数第l-n+1个元素
        // 定义一个哨兵节点 next指向头节点
        ListNode sentinel = new ListNode(-1, head);
        ListNode curr = sentinel;
        for (int i = 0; i < length - n; i++) {
            curr = curr.next;
        }
        // 找到第l-n个节点
        // 跳过第l-n个节点
        curr.next = curr.next.next;
        return sentinel;
    }

    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * 方法二
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {


        // 定义一个哨兵节点 next指向头节点
        ListNode sentinel = new ListNode(-1, head);
        ListNode curr = sentinel;

        Stack<ListNode> stack = new Stack<>();


        // 1. 将所有节点入栈
        while (curr != null){
            stack.push(curr);
            curr = curr.next;
        }

        // 2. 弹栈n
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        stack.peek().next = stack.peek().next.next;
        return sentinel.next;
    }

    /**
     * 双指针法
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {

        // 定义一个哨兵节点 next指向头节点
        ListNode sentinel = new ListNode(-1, head);

        // 定义前后双指针
        ListNode first = sentinel, second = sentinel;

        // 1. first 先走n+1步
        for (int i = 0; i < n; i++) {
            first = first.next;

        }
        // 2. first,second同时前进 当first变为null second就是倒数第n+1个节点
        while (first != null)  {
            first = first.next;
            second = second.next;
        }

        // 3. 删除倒数第n个节点
        second.next = second.next.next;
        return sentinel.next;
    }
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);


        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;

        TestLinkedList.printList(listNode1);

        RemoveNthNodeFromEnd removeNthNodeFromEnd = new RemoveNthNodeFromEnd();

        TestLinkedList.printList(removeNthNodeFromEnd.removeNthFromEnd2(listNode1, 2));
    }
}
