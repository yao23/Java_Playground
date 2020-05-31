package com.leetcode.www;

import com.leetcode.www.TreeNode;

public class DiameterOfBinaryTree { // LC 543 (Facebook)
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Diameter of Binary Tree.
     * Memory Usage: 35.8 MB, less than 99.80% of Java online submissions for Diameter of Binary Tree.
     *
     * https://leetcode.com/problems/diameter-of-binary-tree/discuss/290143/Simple-O(n)-solution-beats-100-java-solution
     */
    int result = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        height(root);
        return result;
    }
    public int height(TreeNode root){
        if (root == null) {
            return 0;
        }
        int l = height(root.left);
        int r = height(root.right);
        result = Math.max(result, l + r); // Calculate the diameter and store it in global variable.
        return 1 + Math.max(l, r);
    }

    /**
     * not working for test case
     *
     * [4,-7,-3,null,null,-9,-3,9,-7,-4,null, 6,null,-6,-6,null,null, 0, 6,  5,null, 9,null,null,-1,-4,null,null,null,-2]
     * [0, 1, 2,   3,   4, 5, 6,7, 8, 9,  10,11,  12,13,14,  15,  16,17, 18,19,  20,21,  22,  23,24,25,  26,  27,  28,29]
     * output: 7
     * expect: 8
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTreeV0(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            return leftHeight + rightHeight;
        }
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            if (leftHeight < rightHeight) {
                return rightHeight + 1;
            } else {
                return leftHeight + 1;
            }
        }
    }
}
