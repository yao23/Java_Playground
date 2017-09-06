package com.leetcode.www;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if( head == null || k == 0 )	return head;
        int len = 1;
        ListNode it = head;

        while( it.next != null ) {
            len++;
            it = it.next;
        }

        if( k  % len == 0 ) return head;
        it.next = head;
        int start = (len - k) % len;

        while( start < 0 ) {
            start += len;
            start %= len;
        }

        while( start > 0 ) {
            it = it.next;
            start--;
        }

        head = it.next;
        it.next = null;

        return head;
    }
}
