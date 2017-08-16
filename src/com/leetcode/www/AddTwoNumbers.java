package com.leetcode.www;

/**
 * Created by liyao on 6/1/17.
 */
public class AddTwoNumbers { // LC 2

    private static ListNode addTwoNumber(ListNode one, ListNode two) {
        int carry = 0;
        ListNode root = new ListNode(0);
        ListNode cur = root;

        while (one != null || two != null) { // one != null || two != null
            int tmp = carry;

            if (one != null) {
                tmp += one.val;
                one = one.next;
            }

            if (two != null) {
                tmp += two.val;
                two = two.next;
            }

            ListNode tmpNode = new ListNode(tmp % 10);
            carry = tmp / 10;

            cur.next = tmpNode;
            cur = cur.next;
        }

        if (carry == 1) {
            cur.next = new ListNode(1);
        }

        return root.next;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(2);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(4);
        node0.next = node1;
        node1.next = node2; // 2 - 4 - 3
        node3.next = node4; // 5 - 6 - 4
        node4.next = node5;

        ListNode root = addTwoNumber(node0, node3);
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.next;
        }
    }

    // beats 64.51%
}