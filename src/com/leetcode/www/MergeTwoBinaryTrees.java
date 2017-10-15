package com.leetcode.www;

public class MergeTwoBinaryTrees { // LC 617
    public TreeNode mergeTreesV2(TreeNode t1, TreeNode t2) { // beats 34.90%
        if (t1 == null && t2 != null) {
            return t2;
        } else if (t1 != null && t2 == null) {
            return t1;
        }

        return mergeTreesUtil(t1, t2);

    }

    private TreeNode mergeTreesUtil(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return null;
        }

        t1.val += t2.val;

        if (mergeTreesUtil(t1.left, t2.left) == null) {
            if (t1.left == null) {
                t1.left = t2.left;
            }
        }

        if (mergeTreesUtil(t1.right, t2.right) == null) {
            if (t1.right == null) {
                t1.right = t2.right;
            }
        }

        return t1;
    }

    public TreeNode mergeTreesV1(TreeNode t1, TreeNode t2) { // beats 91.81%
        TreeNode p1 = new TreeNode(0);
        TreeNode p2 = new TreeNode(0);
        p1.left = t1;
        p2.left = t2;
        dfs(t1, t2, p1, p2);

        return p1.left;
    }

    public void dfs(TreeNode t1, TreeNode t2, TreeNode p1, TreeNode p2) {
        if (t2 == null) {
            return;
        } else {
            if (t1 == null) {
                if (p2.left == t2) {
                    p1.left = t2;
                } else {
                    p1.right = t2;
                }
            } else {
                t1.val += t2.val;
                dfs(t1.left, t2.left, t1, t2);
                dfs(t1.right, t2.right, t1, t2);
            }
        }
    }

    // tree traversal
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) { // beats 53.18%
        if (t1 == null && t2 == null) {
            return null;
        }

        int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
        TreeNode newNode = new TreeNode(val);

        newNode.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        newNode.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

        return newNode;
    }
}