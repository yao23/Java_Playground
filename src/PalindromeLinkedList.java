/**
 * Created by liyao on 7/8/17.
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        } else {
            ListNode first = head, second = head;
            while (second.next != null) {
                first = first.next;
                second = second.next.next;
            }

            return check(head, first.next);
        }
    }

    private boolean check(ListNode first, ListNode second) {
        if (second.next == null) {
            if (first.val == second.val) {
                return true;
            } else {
                return false;
            }
        } else {
            boolean result = check(first, second.next);
            if (result) {
                first = first.next;
                return (first.val == second.val);
            } else {
                return false;
            }
        }
    }
}
