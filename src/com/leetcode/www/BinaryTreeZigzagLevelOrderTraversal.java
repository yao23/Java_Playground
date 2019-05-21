/**
 * Created by liyao on 7/12/17.
 */
package com.leetcode.www;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal { // LC 103
    /**
     * Runtime: 1 ms, faster than 75.92% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     * Memory Usage: 35.1 MB, less than 97.51% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) { // beats 38.80%
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<TreeNode> curQueue = new ArrayList<>();
        curQueue.add(root);
        int order = 1;

        while (!curQueue.isEmpty()) {
            List<TreeNode> nextQueue = new ArrayList<>();
            List<Integer> levelRes = new ArrayList<>();
            for (TreeNode node : curQueue) {
                levelRes.add(node.val);
            }
            result.add(levelRes);

            for (int i = curQueue.size() - 1; i >= 0; i--) {
                TreeNode curNode = curQueue.get(i);

                if (order == 0) { // from left to right
                    if (curNode.left != null) {
                        nextQueue.add(curNode.left);
                    }
                    if (curNode.right != null) {
                        nextQueue.add(curNode.right);
                    }
                } else { // from right to left
                    if (curNode.right != null) {
                        nextQueue.add(curNode.right);
                    }
                    if (curNode.left != null) {
                        nextQueue.add(curNode.left);
                    }
                }
            }

            curQueue = new ArrayList<>(nextQueue);
            order = (order == 0) ? 1 : 0;
        }

        return result;
    }

    /**
     * Runtime: 1 ms, faster than 75.92% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     * Memory Usage: 35.2 MB, less than 97.50% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrderV1(TreeNode root) { // beats 38.80%
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int curNum = 1, order = 0;

        while (!q.isEmpty()) {
            int nextNum = 0;
            List<Integer> levelRes = new ArrayList<>();
            for (int i = 0; i < curNum; i++) {
                TreeNode curNode = q.poll();
                levelRes.add(curNode.val);

                if (curNode.left != null) {
                    q.offer(curNode.left);
                    nextNum++;
                }
                if (curNode.right != null) {
                    q.offer(curNode.right);
                    nextNum++;
                }
            }

            // add level result to final result
            if (order == 0) { // from left to right
                result.add(levelRes);
                order = 1;
            } else { // from right to left
                Collections.reverse(levelRes);
                result.add(levelRes);
                order = 0;
            }

            curNum = nextNum;
        }

        return result;
    }

    public List<List<Integer>> zigzagLevelOrderV0(TreeNode root) { // not working
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result; // don't have to put most code inside else block
        } else {
            int order = 1; // 0 for left to right, 1 for right to left
            Deque<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            int curNum = 1; // cur level node num
            while (!q.isEmpty()) {
                int nextNum = 0;
                List<Integer> levelRes = new ArrayList<>();
                for (int i = 0; i < curNum; i++) {
                    if (order == 0) { // from left to right
                        TreeNode curNode = q.poll();
                        levelRes.add(curNode.val);

                        if (curNode.left != null) {
                            q.offer(curNode.left);
                            nextNum++;
                        }
                        if (curNode.right != null) {
                            q.offer(curNode.right);
                            nextNum++;
                        }
                    } else { // from right to left
                        TreeNode curNode = q.removeLast();
                        levelRes.add(curNode.val);

                        if (curNode.right != null) {
                            q.offer(curNode.right); // cannot update queue here, (it will affect q.removeLast() get curNode)
                            nextNum++;
                        }
                        if (curNode.left != null) {
                            q.offer(curNode.left);
                            nextNum++;
                        }
                    }
                } // end for loop

                result.add(levelRes); // add level result to final result
                curNum = nextNum;
                order = (order == 0) ? 1 : 0; // update order for next level
            } // end while loop

            return result;
        }
    }

    // [3,9,20,null,null,15,7] => [[3],[20,9],[15,7]]
}
