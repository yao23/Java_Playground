package com.leetcode.www;

import java.util.LinkedList;
import java.util.Queue;

public class SecondMinimumNodeInABinaryTree { // LC 671
    private int[] res = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};

    public int findSecondMinimumValue(TreeNode root) { // beats 45.82%
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }
        helper(root);
        if (res[0] != Integer.MAX_VALUE && res[1] != Integer.MAX_VALUE) {
            return res[1];
        } else {
            return -1;
        }
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val < res[0]) {
            res[0] = root.val;
        } else if (root.val < res[1] && root.val > res[0]) {
            res[1] = root.val;
        }
        helper(root.left);
        helper(root.right);
    }

    // BFS
    public int findSecondMinimumValueV1(TreeNode root) {
        int rootVal = root.val;
        int secondSmall =Integer.MAX_VALUE;
        boolean diffFound = false;
        Queue<TreeNode> Q= new LinkedList<>();
        Q.add(root);

        while(!Q.isEmpty()) {
            TreeNode curr=Q.poll();
            if (curr.val != rootVal && curr.val < secondSmall) {
                secondSmall = curr.val;
                diffFound = true;
            }
            if(curr.left != null) {
                Q.add(curr.left);
                Q.add(curr.right);
            }
        }

        return (secondSmall == Integer.MAX_VALUE && !diffFound) ? -1 : secondSmall;
    }

    // DFS
    public int findSecondMinimumValueV0(TreeNode root) { // beats 45.82%
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }

        int left = root.left.val;
        int right = root.right.val;

        // if value same as root value, need to find the next candidate
        if (root.left.val == root.val) {
            left = findSecondMinimumValueV0(root.left);
        }
        if (root.right.val == root.val) {
            right = findSecondMinimumValueV0(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else {
            return right;
        }
    }
}
