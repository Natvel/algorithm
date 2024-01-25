package com.nt.linked.list;

/**
 * @author Enzo
 * @date : 2024/1/22
 */
public class ListNode {

    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
