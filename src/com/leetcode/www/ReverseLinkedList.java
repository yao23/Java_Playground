package com.leetcode.www;

public class ReverseLinkedList { // LC 206
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
     * Memory Usage: 36 MB, less than 99.25% of Java online submissions for Reverse Linked List.
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) { // recursive
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode result = reverseList(head.next);
            head.next.next = head; // reverse pointing from next to head
            head.next = null; // remove next pointer from head
            return result;
        }
    }


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

    public ListNode reverseListV0(ListNode head) { // beats 3.85% (recursive)
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
