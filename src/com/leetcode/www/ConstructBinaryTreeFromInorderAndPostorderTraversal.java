package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal { // LC 106
    /**
     * beats 96.19%
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length) {
            return null;
        }
        return build(inorder,inorder.length - 1,0, postorder,postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart) {
        if (inEnd > inStart) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postStart]);
        if (inEnd == inStart) {
            return root;
        }
        int index = 0;
        // find the index in inorder:
        for (int i = inStart; i >= inEnd; i--){
            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }

        root.right = build(inorder, inStart,index + 1, postorder,postStart - 1);
        root.left = build(inorder,index - 1, inEnd, postorder,postStart - (inStart - index) -1);

        return root;
    }

    /**
     * / beats 69.25%
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeV0(int[] inorder, int[] postorder) {
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