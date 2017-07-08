/**
 * Created by liyao on 7/8/17.
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        } else {
            ListNode first = head, second = head;
            while (second != null && second.next != null) {
                first = first.next;
                second = second.next.next;
            }

            return check(new ListNode[]{head}, first.next);
        }
    }

    private boolean check(ListNode[] first, ListNode second) {
        if (second.next == null) {
            if (first[0].val == second.val) {
                return true;
            } else {
                return false;
            }
        } else {
            boolean result = check(first, second.next);
            if (result) {
                first[0] = first[0].next;
                return (first[0].val == second.val);
            } else {
                return false;
            }
        }
    }

    // [] => true
    // [1] => true
    // [1,2] => false
    // [1,2,1] => true
    // [1,2,2,1] => true
    // [1,2,3,1] => false
}
