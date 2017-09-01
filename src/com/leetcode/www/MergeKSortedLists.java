package com.leetcode.www;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if( lists == null || lists.length == 0 )
            return null;
        Comparator<ListNode> comparator = new Comparator<ListNode>() {
            public int compare(ListNode m, ListNode n) {
                if( m.val == n.val )	return 0;
                else if( m.val > n.val )
                    return 1;
                return -1;
            }
        };

        PriorityQueue<ListNode> PQ = new PriorityQueue<ListNode>(lists.length, comparator);
        for( int i = 0; i < lists.length; i++ ) {
            if( lists[i] != null )
                PQ.add(lists[i]);
        }
        ListNode head = null, cur = null;
        while( !PQ.isEmpty() ) {
            if( head == null ) {
                head = PQ.poll();
                cur = head;
            }
            else {
                cur.next = PQ.poll();
                cur = cur.next;
            }
            if( cur.next != null )
                PQ.add(cur.next);
        }

        return head;
    }
}
