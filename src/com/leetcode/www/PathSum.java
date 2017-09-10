package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class PathSum { // LC 112
    public boolean hasPathSum(TreeNode root, int sum) { // beats 9.74% (recursive)
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val));
    }

    public boolean hasPathSumV0(TreeNode root, int sum) { // beats 1.58% (iterative)
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> sums = new ArrayDeque<>();

        stack.push(root);
        sums.push(sum);

        while (!stack.isEmpty() && (root!=null)) {
            int value = sums.pop();
            TreeNode top = stack.pop();

            if (top.left == null && top.right==null && top.val==value) {
                return true;
            }
            if (top.right != null) {
                stack.push(top.right);
                sums.push(value - top.val);
            }
            if (top.left != null) {
                stack.push(top.left);
                sums.push(value - top.val);
            }

        }
        return false;
    }
}

// [], 1 => false
