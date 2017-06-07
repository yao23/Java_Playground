/**
 * Created by liyao on 6/6/17.
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        if (head.next == null) {
            if (n <= 1) {
                head = null;
                return head;
            } else {
                return head;
            }
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode end = dummy;

        int i = 0;

        while (i < n && end.next != null) {
            end = end.next;

            i++;
        }

        if (i < n) {
            return head;
        } else { // i == n

            while (end.next != null) {
                cur = cur.next;
                end = end.next;
            }

            cur.next = cur.next.next;

            return dummy.next;
        }
    }
}
