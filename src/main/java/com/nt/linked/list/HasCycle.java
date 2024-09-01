package com.nt.linked.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Enzo
 * @date : 2024/9/1
 */

public class HasCycle {

    public boolean hasCycle(ListNode head) {
        Map<ListNode, ListNode> map = new HashMap();
        // 遍历链表
        while (head != null) {
            if (map.containsKey(head)) {
                return Boolean.TRUE;
            }
            map.put(head, head);
            head = head.next;
        }
        return Boolean.FALSE;
    }

    public boolean hasCycle1(ListNode head) {
        // 基于快慢指针的思想
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }


}
