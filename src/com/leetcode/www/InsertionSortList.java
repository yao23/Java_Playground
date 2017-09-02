package com.leetcode.www;

/**
 * Created by liyao on 6/28/17.
 */
public class InsertionSortList { // LC 147
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            return insertSort(head);
        }
    }

    private ListNode insertSort(ListNode head) {
        if (head.next == null) {
            return head;
        } else {
            ListNode newHead = insertSort(head.next);
            ListNode dummy = new ListNode(0);
            dummy.next = newHead;
            return insert(dummy, head);
        }
    }

    private ListNode insert(ListNode head, ListNode insertedNode) {
        ListNode pre = head, cur = head.next;

        while (cur != null && cur.val < insertedNode.val) {
            pre = cur;
            cur = cur.next;
        }

        if (cur == null) {
            pre.next = insertedNode;
            insertedNode.next = null;
        } else {
            insertedNode.next = cur;
            pre.next = insertedNode;
        }

        return head.next;
    }

    // [] => []
    // [1] => [1]
    // [2,1] => [1,2]
    // [-1,2,1] => [-1,1,2]
    // [6,5,3,1,8,7,2,4] => [1,2,3,4,5,6,7,8]

    // beats 67.67%, 39ms
}
