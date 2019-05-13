package com.leetcode.www;

public class ReverseLinkedList { // LC 206
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
     * Memory Usage: 36 MB, less than 99.25% of Java online submissions for Reverse Linked List.
     *
     * @param head node
     * @return final reversed head node
     */
    public ListNode reverseList(ListNode head) { // recursive (non-tail recursion, finish later)
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode result = reverseList(head.next);
            head.next.next = head; // reverse pointing from next to head
            head.next = null; // remove next pointer from head
            return result;
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
     * Memory Usage: 36.4 MB, less than 99.25% of Java online submissions for Reverse Linked List.
     *
     * @param head node
     * @return final reversed head node
     */
    public ListNode reverseListV3(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode head, ListNode newHead) { // recursive (tail recursion, finish previously)
        if (head == null) {
            return newHead;
        } else {
            ListNode nextNode = head.next; // handle next next node
            head.next = newHead; // reverse pointing
            return reverse(nextNode, head);
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
     * Memory Usage: 36.1 MB, less than 99.25% of Java online submissions for Reverse Linked List.
     *
     * @param head node
     * @return final reversed head node
     */
    public ListNode reverseListV2(ListNode head) { // iterative
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode nextNode = head.next;
            head.next = null; // remove next pointing for first node
            while (nextNode != null) {
                ListNode nextNextNode = nextNode.next; // handle next next node
                nextNode.next = head; // reverse pointing
                head = nextNode;
                nextNode = nextNextNode;
            }

            return head;
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
