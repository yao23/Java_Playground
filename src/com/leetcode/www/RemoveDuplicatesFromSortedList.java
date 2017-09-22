package com.leetcode.www;

/**
 * Created by liyao on 6/4/17.
 */
public class RemoveDuplicatesFromSortedList { // LC 83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        boolean isDuplicate = false;

        while (cur != null) {
            if (pre.val == cur.val) { // same with pre node
                isDuplicate = true;
            } else { // different from pre node
                if (isDuplicate) {
                    pre.next = cur;
                }
                pre = pre.next;
                isDuplicate = false;
            }
            cur = cur.next;
        }

        if (isDuplicate) {
            pre.next = null;
        }

        return head;
    }

    // []
    // [1,1,1]
    // [1,1,2]
    // [1,1,2,3,3]
    // [1,2,3,4,5]

    // beats 17.93%
}
