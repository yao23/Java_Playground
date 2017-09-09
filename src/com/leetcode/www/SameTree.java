package com.leetcode.www;

public class SameTree { // LC 100
    public boolean isSameTree(TreeNode p, TreeNode q) { // beats 1.30%
        boolean p_null = (p == null);
        boolean q_null = (q == null);

        if (p_null != q_null) {
            return false;
        } else if (p == null && q == null) {
            return true;
        } else if (p.val == q.val) {
            return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        } else {
            return false;
        }
    }
}

// [], [] => true
