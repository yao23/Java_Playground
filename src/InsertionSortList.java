/**
 * Created by liyao on 6/28/17.
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode pre = dummy, cur = head;
            while (cur != null) {
                insert(dummy, cur, pre);
                pre = cur;
                cur = cur.next;
            }

            return dummy.next;
        }
    }

    private void insert(ListNode head, ListNode cur, ListNode curPre) {
        ListNode pre = head, ptr = head.next;

        while (ptr != null && ptr.val < cur.val) {
            pre = cur;
            cur = cur.next;
        }

        if (ptr == null) {
            curPre.next = cur.next;
            ptr.next = cur;
            cur.next = null;
        } else {
            curPre.next = cur.next;
            cur.next = ptr;
            pre.next = cur;
        }
    }
}
