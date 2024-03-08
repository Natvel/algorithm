package com.nt.linked.list;

/**zz
 * @author Enzo
 * @date : 2024/1/22
 */
public class ReverseLinkedList {
    /**
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        // 定义一个指针 指向当前访问的节点 以及上一个节点
        ListNode curr = head;
        ListNode prev = null;

        // 依次迭代链表中的节点 将next指针指向prev
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            // 更新指针 当前指针变为之前的next 上一个指针变为curr
            prev = curr;
            curr = nextNode;
        }
        // prev指向的就是末尾的节点 也就是翻转之后的头结点

        return prev;
    }

    /**
     * 方法二
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        // 递归先写出跳出循环条件
        if (head == null|| head.next == null) {
            return head;
        }
        // 递归调用剩余所有节点
        ListNode restNode = head.next;
        ListNode reverseRest = reverseList2(restNode);
        // 把当前阶段接在反转之后后的链表末尾
        restNode.next = head;
        // 当前节点就是链表末尾 直接指向null
        head.next = null;
        return reverseRest ;
    }

    public static void main(String[] args) {
        // 构建一个链表 把所有节点创建出来 然后连接

        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(17);


        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;
        ReverseLinkedList list = new ReverseLinkedList();
        TestLinkedList.printList(list.reverseList(listNode1));

    }
}
