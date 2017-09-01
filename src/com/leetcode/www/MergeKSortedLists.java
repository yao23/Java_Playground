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

    public static ListNode mergeKListsV0(ListNode[] lists) { // beats 78.03%
        return partion(lists,0,lists.length - 1);
    }

    private static ListNode partion(ListNode[] lists, int s, int e) {
        if (s == e) {
            return lists[s];
        }
        if (s < e) {
            int m = s + (e - s) / 2;
            ListNode l1 = partion(lists, s, m);
            ListNode l2 = partion(lists,m + 1, e);
            return merge(l1, l2);
        } else {
            return null;
        }
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
