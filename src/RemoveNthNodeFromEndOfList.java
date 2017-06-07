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

    // [] 0 => []
    // [1] 1 => []
    // [1,2,3,4,5] 2 => [1,2,3,5]
    // [1,2,3,4,5] 5 => [2,3,4,5]
    // [1,2,3,4,5] 6 => [1,2,3,4,5] (LeetCode given [2,3,4,5])

    // beats 21.61%
}
