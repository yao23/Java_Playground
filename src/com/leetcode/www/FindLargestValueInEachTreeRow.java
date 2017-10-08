package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindLargestValueInEachTreeRow { // LC 515
    public List<Integer> largestValues(TreeNode root) { // beats 37.33%
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
