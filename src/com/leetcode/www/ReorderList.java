package com.leetcode.www;

public class ReorderList { // LC 143
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode mid = findMid(head);
        ListNode secondHead = mid.next;
        mid.next = null;
        secondHead = reverse(secondHead);
        head = merge(head, secondHead);
    }

    private ListNode findMid(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
        return head;
    }

    private ListNode merge(ListNode head, ListNode secHead) {
        ListNode cur = head;

        while (secHead != null) {
            ListNode tmp = secHead.next;
            secHead.next = cur.next;
            cur.next = secHead;
            cur = cur.next.next;
            secHead = tmp;
        }

        return head;
    }
}

// [1,2,3,4] => [1, 4, 2, 3]

// beats 59.71%
