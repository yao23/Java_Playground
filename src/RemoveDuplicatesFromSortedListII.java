/**
 * Created by liyao on 6/4/17.
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode fakeHead = new ListNode(0);
            fakeHead.next = head;
            ListNode pre = fakeHead;
            ListNode cur = head;
            ListNode post = head.next;
            boolean isDuplicate = false;

            while (post != null) {
                if (cur.val == post.val) {
                    if (!isDuplicate) {
                        isDuplicate = true;
                    }
                } else {
                    if (isDuplicate) {
                        pre.next = post;
                        cur = post;

                        isDuplicate = false;
                    } else {
                        pre = pre.next;
                        cur = cur.next;
                    }
                }

                post = post.next;
            }

            if (isDuplicate) {
                pre.next = null;
            }

            return fakeHead.next;
        }
    }

    // []
    // [1,1,1,1,1]
    // [1,1,1,2,3]
    // [1,1,1,2,3]
    // [1,2,3,3,4,4,5]
    // [1,2,3,4,5]
}
