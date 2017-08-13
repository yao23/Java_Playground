package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal { // LC 105
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndexMap.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inIndexMap);
    }

    private TreeNode helper(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd) {
            return null;
        }
        int val = pre[preStart];
        int iIn = inMap.get(val);
        int leftSize = iIn - inStart;
        TreeNode root = new TreeNode(val);
        root.left = helper(pre, preStart + 1, preStart + leftSize, in, inStart, inStart + leftSize - 1, inMap);
        root.right = helper(pre, preStart + leftSize + 1, preEnd, in, inStart + leftSize + 1, inEnd, inMap);

        return root;
    }
}

// [],[] => []

// beats 82.18%
