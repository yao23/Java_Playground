package com.leetcode.www;

public class ReverseLinkedList { // LC 206
    public ListNode reverseListV1(ListNode head) { // beats 27.71% (iterative)
        if (head == null || head.next == null) {
            return head;
        }
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

    public ListNode reverseList(ListNode head) { // beats 3.85% (recursive)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        ListNode newHead = reverseList(nextNode);
        nextNode.next = head;
        head.next = null;
        return newHead;
    }
}
