package com.leetcode.www;

public class ConvertSortedArrayToBinarySearchTree { // LC 108
    public TreeNode sortedArrayToBST(int[] num) { // beats 11.10%
        return generateBST(num, 0, num.length-1);
    }
    private TreeNode generateBST(int[] num, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = generateBST(num, start, mid - 1);
        root.right = generateBST(num, mid + 1, end);
        return root;
    }
}
