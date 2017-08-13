package com.leetcode.www;

public class ReverseLinkedListII { // LC 92
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastTail = dummy; // scanner

        for (int i = 0; i < m - 1; i++) {
            lastTail = lastTail.next;
        }

        ListNode tail = lastTail.next;
        ListNode cur = tail.next;

        for (int i = 0; i < n - m; i++) {
            tail.next = cur.next;
            cur.next = lastTail.next;
            lastTail.next = cur;
            cur = tail.next;
        }

        return dummy.next;
    }
}

// Given 1->2->3->4->5->NULL, m = 2 and n = 4 => return 1->4->3->2->5->NULL.
// [5], 1, 1 => [5]

// beats 26.99%