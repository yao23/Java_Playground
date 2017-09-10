package com.leetcode.www;

public class ConvertSortedListToBinarySearchTree {
    private static ListNode CurNode;

    public TreeNode sortedListToBST(ListNode head) {
        if( head == null )	return null;
        ListNode cur = head;
        int len = 0;
        while( cur != null ) {
            len++;
            cur = cur.next;
        }

        CurNode = head;
        return SortedListToBST(0, len - 1);
    }
    private TreeNode SortedListToBST(int start, int end) {
        if( start > end )	return null;
        // same as mid = (start + end) / 2, avoid overflow
        int mid = start + (end - start) / 2;
        TreeNode left = SortedListToBST(start, mid - 1);
        TreeNode root = new TreeNode(CurNode.val);
        root.left = left;
        CurNode = CurNode.next;
        root.right = SortedListToBST(mid + 1, end);
        return root;
    }
}
