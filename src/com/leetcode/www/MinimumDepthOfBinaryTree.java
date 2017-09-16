package com.leetcode.www;

/**
 * Created by liyao on 7/12/17.
 */
public class MinimumDepthOfBinaryTree { // LC 111
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            if (root.left == null && root.right == null) {
                return 1;
            } else if (root.left != null && root.right != null) {
                return 1 + Math.min(minDepth(root.left), minDepth(root.right));
            } else {
                return 1 + ((root.left != null) ? minDepth(root.left) : minDepth(root.right)); // test case 2
            }
        }
    }

    public int minDepthV1(TreeNode root) { // beats 14.32
        if (root == null) {
            return 0;
        } else {
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            return (left == 0 || right == 0) ? (left + right + 1) : (Math.min(left, right) + 1);
        }
    }

    // [] => 0
    // [1,2] => 2
    // [1,3,4,null,null,5,6] => 2

    // beats 16.35%
}
