package com.leetcode.www;

/**
 * Created by liyao on 6/11/17.
 */
public class SwapNodesInPairs { // LC 24
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode next = head.next;
        ListNode pre = dummy;

        while (cur != null && next != null) {
            ListNode tmp = next.next;
            pre.next = next;
            next.next = cur;
            cur.next = tmp;

            pre = cur;
            cur = cur.next;
            next = (cur != null) ? cur.next : null; // null pointer exception if without checking
        }

        return dummy.next;
    }

    // [] => []
    // [1,2,3,4] => [2,1,4,3]
    // [1,2,3,4,5] => [2,1,4,3,5]

    // beats 30.26%
}
