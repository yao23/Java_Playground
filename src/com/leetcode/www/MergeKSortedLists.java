package com.leetcode.www;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists { // LC 23
    public ListNode mergeKLists(ListNode[] lists) { // beats 53.73%
        if (lists == null || lists.length == 0) {
            return null;
        }
        Comparator<ListNode> comparator = (m, n) -> {
            if (m.val == n.val)	{
                return 0;
            } else if (m.val > n.val) {
                return 1;
            } else {
                return -1;
            }
        };

        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, comparator);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }
        ListNode head = null, cur = null;
        while (!pq.isEmpty()) {
            if (head == null) {
                head = pq.poll();
                cur = head;
            } else {
                cur.next = pq.poll();
                cur = cur.next;
            }
            if (cur.next != null) {
                pq.add(cur.next);
            }
        }

        return head;
    }
}
