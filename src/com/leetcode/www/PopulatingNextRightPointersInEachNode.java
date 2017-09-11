package com.leetcode.www;

public class PopulatingNextRightPointersInEachNode {
    public void connect(TreeLinkNode root) {
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
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}