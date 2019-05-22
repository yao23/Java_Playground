package com.leetcode.www;

public class DiameterOfBinaryTree {
    /**
     * not working for test case
     *
     * [4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]
     * output: 7
     * expect: 8
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
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
