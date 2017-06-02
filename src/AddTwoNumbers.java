/**
 * Created by liyao on 6/1/17.
 */
public class AddTwoNumbers {

    public static ListNode addTwoNumber(ListNode one, ListNode two) {
        int carry = 0;
        ListNode root = new ListNode(0);
        ListNode cur = null;
        root.next = cur;
        int i = 0;

        while (one != null && two != null) { // one != null || two != null
            int tmp = one.val + two.val;
            ListNode tmpNode = new ListNode(tmp % 10);
            carry = tmp / 10;

            if (i == 0) {
                cur = tmpNode;
            } else {
                cur.next = tmpNode;
                cur = cur.next;
            }

            one = one.next;
            two = two.next;
            i++;
        }

        if (one != null) {

        }

        if (two != null) {

        }

        if (carry == 1) {

        }

        return root.next;
    }
}