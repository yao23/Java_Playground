/**
 * Created by liyao on 6/6/17.
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode headSmall = new ListNode(0);
        ListNode headLarge = new ListNode(0);
        ListNode pointerSmall = headSmall;
        ListNode pointerLarge = headLarge;

        while (head != null) {
            int cur = head.val;

            if (cur < x) {
                pointerSmall.next = new ListNode(cur);
                pointerSmall = pointerSmall.next;
            } else {
                pointerLarge.next = new ListNode(cur);
                pointerLarge = pointerLarge.next;
            }

            head = head.next;
        }

        pointerSmall.next = headLarge.next;

        return headSmall.next;
    }
}
