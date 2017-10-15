package com.leetcode.www;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView { // LC 199
    // BFS, reverse level traversal
    public List<Integer> rightSideView(TreeNode root) { // beats 22.08%
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }

        queue.offer(root);
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) {
                    result.add(cur.val);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }

        }
        return result;
    }

    /**
     * 1. Each depth of the tree only select one node.
     * 2. View depth is current size of result list.
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewV0(TreeNode root) { // beats 22.08%
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    private void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }
}
