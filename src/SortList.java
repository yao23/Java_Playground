/**
 * Created by liyao on 7/8/17.
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        return sort(head);
    }

    private ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode midPre = findMidPre(head);
            ListNode rightHalfHead = midPre.next;
            midPre.next = null; // break into 2 halves to merge sort
            ListNode leftHalfHead = sort(head);
            rightHalfHead = sort(rightHalfHead);
            return merge(leftHalfHead, rightHalfHead);
        }
    }

    private ListNode findMidPre(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = new ListNode(head1.val);
                head1 = head1.next;
            } else {
                cur.next = new ListNode(head2.val);
                head2 = head2.next;
            }
            cur = cur.next;
        }

        while (head1 != null) {
            cur.next = new ListNode(head1.val);
            head1 = head1.next;
            cur = cur.next;
        }

        while (head2 != null) {
            cur.next = new ListNode(head2.val);
            head2 = head2.next;
            cur = cur.next;
        }

        return dummy.next;
    }

    // Sort a linked list in O(n log n) time using constant space complexity.

    // [] => []
    // [1] => [1]
    // [2,1] => [1,2]
    // [2,3,1] => [1,2,3]
    // [2,4,3,1] => [1,2,3,4]
    // [2,3,5,4,1] => [1,2,3,4,5]

    // beats 10.62%
}
