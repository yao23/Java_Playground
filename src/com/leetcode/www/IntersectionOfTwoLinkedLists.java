package com.leetcode.www;

public class IntersectionOfTwoLinkedLists { // LC 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) { // beats 13.74%
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        if (lenA == lenB) {
            return findIntersection(headA, headB);
        } else {
            ListNode hA = headA;
            ListNode hB = headB;
            if (lenA < lenB) {
                hB = getStartNode(headB, lenB - lenA);
            } else {
                hA = getStartNode(headA, lenA - lenB);
            }
            return findIntersection(hA, hB);
        }
    }

    private ListNode getStartNode(ListNode head, int step) {
        for (int i = 0; i < step; i++) {
            head = head.next;
        }
        return head;
    }

    private ListNode findIntersection(ListNode headA, ListNode headB) {
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }

        return null;
    }

    private int getLength(ListNode head) {
        int len = 0;

        while (head != null) {
            head = head.next;
            len++;
        }

        return len;
    }
}
