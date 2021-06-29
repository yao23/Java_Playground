package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal { // LC 105
    /**
     * beats 97.86%
     * [],[] => []
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Preorder: Traverse and construct the tree
        // Inorder: Check leftmost boundary
        int[] curIn = {0};
        int[] curPre = {0};

        return construct(inorder, preorder, curIn, curPre, Integer.MAX_VALUE);
    }

    private TreeNode construct(int[] in, int[] pre, int[] curIn, int[] curPre, int toCheck) {
        if (curPre[0] == pre.length || in[curIn[0]] == toCheck) {
            curIn[0]++;
            return null;
        }
        TreeNode root = new TreeNode(pre[curPre[0]]);
        curPre[0]++;
        root.left = construct(in, pre, curIn, curPre, root.val);
        root.right = construct(in, pre, curIn, curPre, toCheck);
        return root;
    }

    public TreeNode buildTreeV0(int[] preorder, int[] inorder) { // beats 82.18%
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


