package com.leetcode.www;

public class RotateList { // LC 61
    public ListNode rotateRight(ListNode head, int k) { // beats 54.34%
        if (head == null || k == 0)	{
            return head;
        }
        int len = 1;
        ListNode it = head;

        while (it.next != null) {
            len++;
            it = it.next;
        }

        if (k  % len == 0) {
            return head;
        }
        it.next = head;
        int start = (len - k) % len;

        while (start < 0) {
            start += len;
            start %= len;
        }

        while (start > 0) {
            it = it.next;
            start--;
        }

        head = it.next;
        it.next = null;

        return head;
    }
}

// Given 1->2->3->4->5->NULL and k = 2,
// return 4->5->1->2->3->NULL.