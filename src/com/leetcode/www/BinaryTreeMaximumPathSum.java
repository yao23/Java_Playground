package com.leetcode.www;

import com.leetcode.www.TreeNode;

/**
 * Created by liyao on 6/14/17.
 */
public class BinaryTreeMaximumPathSum { // LC 124 (Facebook)
    private int helper(TreeNode root, int[] result) {
        if (root == null) {
            return 0;
        } else {
            int left = helper(root.left, result);
            int right = helper(root.right, result);
            int single = Math.max(root.val, root.val + Math.max(left,right)); // single or one half
            int max = Math.max(single, root.val + left + right); // single or arch
            if (max > result[0]) {
                result[0] = max;
            }
            return single;
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Maximum Path Sum.
     * Memory Usage: 41.3 MB, less than 9.52% of Java online submissions for Binary Tree Maximum Path Sum.
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int[] result = new int[]{Integer.MIN_VALUE};
        helper(root, result);
        return result[0];
    }

    // [] => -2147483648
    // [-3] => -3
    // [1,2,3] => 6
    // [5,3,4,1,2,6,7] => 21, [2,3,5,4,7], root 5 use both left and right single path
    // [5,-3,-4,1,2,6,7] => 9, [6,-4,7], root 5 don't use left or right single path, arch in root 6 as max

    // beats 48.00%
}
