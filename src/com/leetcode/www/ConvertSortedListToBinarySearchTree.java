package com.leetcode.www;

public class ConvertSortedListToBinarySearchTree {
    private static ListNode curNode;

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
}
