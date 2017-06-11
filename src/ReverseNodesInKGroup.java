/**
 * Created by liyao on 6/11/17.
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode last = head;
        int counter = 1;

        while (last != null) {
            if (counter == k) {
                ListNode curHead = cur; // initialization in current group
                ListNode curNext = cur.next;
                ListNode nextHead = last.next;
                last.next = null; // break temporarily for reversion in iterative way

                while (curNext != null) { // reverse k nodes
                    ListNode tmp = curNext.next;
                    curNext.next = cur;
                    cur = curNext;   // update for next two nodes reversion
                    curNext = tmp;
                }

                pre.next = last;
                curHead.next = nextHead; // link head and tail in current group

                pre = curHead; // update for next k-group
                cur = nextHead;
                last = nextHead;
                counter = 1;
            } else {
                counter++;
                last = last.next;
            }
        }

        return dummy.next;
    }

    // [], 1 => []
    // [1,2,3,4,5], 2 => [2,1,4,3,5]
    // [1,2,3,4,5], 3 => [3,2,1,4,5]
    // [1,2], 1 => [1,2]

    // beats 53.64%
}
