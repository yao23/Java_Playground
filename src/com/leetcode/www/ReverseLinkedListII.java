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

        ListNode tail = lastTail.next; // lastTail: 1, tail: 2
        ListNode cur = tail.next; // cur: 3

        // nodes m ~ n
        for (int i = 0; i < n - m; i++) {
            tail.next = cur.next; // 2.next -> 4 (2.next -> 5 in 2nd round)
            cur.next = lastTail.next; // 3.next -> 2 (4.next -> 3 in 2nd round)
            lastTail.next = cur; // 1.next -> 3 (1.next -> 4 in 2nd round)
            cur = tail.next; // cur: 4 (cur: 5 in 2nd round)
        }

        return dummy.next;
    }
}

// Given 1->2->3->4->5->NULL, m = 2 and n = 4 => return 1->4->3->2->5->NULL.
// [5], 1, 1 => [5]

// beats 26.99%