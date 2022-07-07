package com.leetcode.www;
/**
 * Created by liyao on 7/12/17.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeLevelOrderTraversal { // LC 102
    /**
     * Runtime: 1 ms, faster than 75.54% of Java online submissions for Binary Tree Level Order Traversal.
     * Memory Usage: 35.4 MB, less than 98.97% of Java online submissions for Binary Tree Level Order Traversal.
     *
     * beats 11.00%
     *
     * [3,9,20,null,null,15,7] => [[3],[9,20],[15,7]]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int cur = 1;

        while (!q.isEmpty()) {
            int next = 0;
            List<Integer> levelRes = new ArrayList<>();
            for (int i = 0; i < cur; i++) {
                TreeNode curNode = q.poll();
                levelRes.add(curNode.val);

                if (curNode.left != null) {
                    q.offer(curNode.left);
                    next++;
                }
                if (curNode.right != null) {
                    q.offer(curNode.right);
                    next++;
                }
            }

            cur = next;
            result.add(levelRes);
        }

        return result;
    }
}
