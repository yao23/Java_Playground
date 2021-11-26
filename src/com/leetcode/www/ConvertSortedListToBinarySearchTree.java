package com.leetcode.www;

public class ConvertSortedListToBinarySearchTree { // LC 109
    private static ListNode curNode;

    /**
     * beats 47.53%
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        curNode = head;
        return sortedListToBST(0, len - 1);
    }

    private TreeNode sortedListToBST(int start, int end) {
        if (start > end) {
            return null;
        }
        // same as mid = (start + end) / 2, avoid overflow
        int mid = start + (end - start) / 2;
        TreeNode left = sortedListToBST(start, mid - 1);
        TreeNode root = new TreeNode(curNode.val);
        root.left = left;
        curNode = curNode.next;
        root.right = sortedListToBST(mid + 1, end);
        return root;
    }

    /**
     *   beats 47.53%
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBSTV1(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null){
            TreeNode treeNode = new TreeNode(head.val);
            return treeNode;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.next.val);
        ListNode temp = slow.next.next;
        slow.next = null;
        root.left = sortedListToBSTV1(head);
        root.right = sortedListToBSTV1(temp);
        return root;
    }


    public TreeNode sortedListToBSTV0(ListNode head) { // beats 47.53%
        if (head == null) {
            return null;
        }
        return toBST(head,null);
    }
    private TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) {
            return null;
        }

        while (fast != tail && fast.next != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next, tail);
        return thead;
    }
}
