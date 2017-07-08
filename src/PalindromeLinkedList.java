/**
 * Created by liyao on 7/8/17.
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        } else {
            ListNode first = head, second = head;
            int len = 1;

            while (second != null && second.next != null) { // find mid node in O(n)
                first = first.next;
                second = second.next.next;
                len += 2;
            }

            if (second == null) { // list length is even number
                len -= 1;
            }

            if (len % 2 == 0) { // go over 2 halves in O(n)
                return check(new ListNode[]{head}, first); // first locates on head in second half
            } else {
                return check(new ListNode[]{head}, first.next); // first.next locates on head in second half
            }
        }
    }

    private boolean check(ListNode[] first, ListNode second) { // first array holds 1 element, space O(1)
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
