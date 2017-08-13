package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal { // LC 106
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndexMap.put(inorder[i], i);
        }
        return helper(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inIndexMap);
    }

    private TreeNode helper(int[] post, int postStart, int postEnd, int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd) {
            return null;
        }
        int val = post[postEnd];
        int iIn = inMap.get(val);
        int leftSize = iIn - inStart;
        TreeNode root = new TreeNode(val);
        root.left = helper(post, postStart, postStart + leftSize - 1, in, inStart, inStart + leftSize - 1, inMap);
        root.right = helper(post, postStart + leftSize, postEnd - 1, in, inStart + leftSize + 1, inEnd, inMap);

        return root;
    }
}

// beats 69.25%