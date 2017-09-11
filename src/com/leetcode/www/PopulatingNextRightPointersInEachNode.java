package com.leetcode.www;

public class PopulatingNextRightPointersInEachNode { // LC 116
    public void connect(TreeLinkNode root) { // beats 29.08%
        if (root == null) {
            return;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            root.right.next = (root.next == null) ? null : root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }

    public void connectV0(TreeLinkNode root) { // beats 29.08%
        TreeLinkNode levelStart = root;
        while (levelStart != null) {
            TreeLinkNode cur = levelStart;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next=cur.next.left;
                }
                cur=cur.next;
            }
            levelStart = levelStart.left;
        }
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}