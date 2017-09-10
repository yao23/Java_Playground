package com.leetcode.www;

public class PathSum { // LC 112
    public boolean hasPathSum(TreeNode root, int sum) { // beats 9.74
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val));
    }
}

// [], 1 => false
