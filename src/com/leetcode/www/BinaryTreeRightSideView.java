package com.leetcode.www;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.leetcode.www.TreeNode;

public class BinaryTreeRightSideView { // LC 199 (Facebook)
    /**
     * Runtime: 1 ms, faster than 75.35% of Java online submissions for Binary Tree Right Side View.
     * Memory Usage: 38.8 MB, less than 5.88% of Java online submissions for Binary Tree Right Side View.
     *
     * Divide and Conquer
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) { // beats 22.08%
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> left = rightSideView(root.left);
        List<Integer> right = rightSideView(root.right);
        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        for (int i = 0; i < Math.max(left.size(), right.size()); i++) {
            if (i >= right.size()) {
                res.add(left.get(i));
            } else {
                res.add(right.get(i));
            }
        }
        return res;
    }

    /**
     * Runtime: 1 ms, faster than 75.35% of Java online submissions for Binary Tree Right Side View.
     * Memory Usage: 38.2 MB, less than 5.88% of Java online submissions for Binary Tree Right Side View.
     *
     * BFS, reverse level traversal
     * beats 22.08%
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewV1(TreeNode root) {
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
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Right Side View.
     * Memory Usage: 38.3 MB, less than 5.88% of Java online submissions for Binary Tree Right Side View.
     *
     * line 95 to 97 handling following case
     * Input: [1,2,3,4]
     * Output: [1,3]
     * Expected: [1,3,4]
     *
     * beats 22.08%
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewV0(TreeNode root) {
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
