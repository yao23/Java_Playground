package com.clouds.www;

public class InorderMorrisTraversal {
    public void inorderMorrisTraversal(TreeNode root) { // time: O(logn) space: O(1)
        TreeNode cur = root;
        TreeNode pre = null;

        while (cur != null) {
            if (cur.left == null) { // no left child -> print current
                System.out.println(cur.val);
                cur = cur.right;
            } else { // has left child
                // step 1: find predecessor in left subtree
                pre = cur.left;

                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                // step 2: connect predecessor and current
                if (pre.right == null) { // not connected -> connected
                    pre.right = cur;
                    cur = cur.left;
                } else { // connected -> print current
                    pre.right = null;
                    System.out.println(cur.val);
                    cur = cur.right;
                }
            }
        }
    }
}
