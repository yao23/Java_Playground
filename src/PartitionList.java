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

    // [] 0 => []
    // [1] 1 => [1]
    // [1,4,3,2,5,2] 3 => [1,2,2,4,3,5]
    // [1,4,3,2,5,2] 1 => [1,4,3,2,5,2]
    // [1,4,3,2,5,2] 2 => [1,4,3,2,5,2]
    // [1,4,3,2,5,2] 4 => [1,3,2,2,4,5]

    // beats 7.98%
}
