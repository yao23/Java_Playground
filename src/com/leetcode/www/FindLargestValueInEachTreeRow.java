package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindLargestValueInEachTreeRow { // LC 515
    // DFS
    // pre-order traverse idea. Use depth to expand result list size and put the max value in the appropriate position
    public List<Integer> largestValues(TreeNode root) { // beats 97.01%
        List<Integer> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res, int d) {
        if (root == null) {
            return;
        }
        // expand list size
        if (d == res.size()) {
            res.add(root.val);
        } else{ //or set value
            res.set(d, Math.max(res.get(d), root.val));
        }
        helper(root.left, res, d + 1);
        helper(root.right, res, d + 1);
    }

    // BFS
    public List<Integer> largestValuesV0(TreeNode root) { // beats 37.33%
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int cur = 1;
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int next = 0;
            for (int i = 0; i < cur; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                    next++;
                    if (curNode.left.val > max) {
                        max = curNode.left.val;
                    }
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                    next++;
                    if (curNode.right.val > max) {
                        max = curNode.right.val;
                    }
                }
            }
            cur = next;
            res.add(max);
        }
        res.remove(res.size() - 1);
        return res;
    }
}
