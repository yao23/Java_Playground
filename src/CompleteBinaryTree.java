/**
 * Created by liyao on 7/7/17.
 */

import java.util.LinkedList;

public class CompleteBinaryTree {
    public boolean isComplete(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            if (root.left == null && root.right == null) {
                return true;
            } else {
                LinkedList<TreeNode> queue = new LinkedList<>();
                queue.offer(root);
                boolean hasNullBefore = false;

                while (!queue.isEmpty()) {
                    TreeNode curNode = queue.poll();
                    if (curNode.left != null) {
                        if (hasNullBefore) {
                            return false;
                        } else {
                            queue.offer(curNode.left);
                        }
                    } else {
                        hasNullBefore = true;
                    }
                    if (curNode.right != null) {
                        if (hasNullBefore) {
                            return false;
                        } else {
                            queue.offer(curNode.right);
                        }
                    } else {
                        hasNullBefore = true;
                    }
                }

                return true;
            }
        }
    }

    public boolean isCompleteV1(TreeNode root) {
        if (root == null) {
            return true;
        }

        //first of all do the count
        int count = countNumber(root);

        int index = 0;

        return helper(root, index, count); // use recursion to do it
    }

    private boolean helper(TreeNode root, int index, int count){
        if (root == null) {
            return true;
        }

        if (index >= count) {
            return false;
        } else {
            return (helper(root.left, index * 2 + 1, count) && helper(root.right, index * 2 + 2, count));
        }
    }

    private int countNumber(TreeNode root){
        if (root == null) {
            return 0;
        }

        return countNumber(root.left) + countNumber(root.right) + 1;
    }

    public boolean isCompleteV0(TreeNode root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return true;
        } else {
            int h = height(root), curHeight = 1;
            LinkedList<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int curLevel = 1;
            int nullIdx = Integer.MAX_VALUE, valIdx = -1;
            while (!q.isEmpty()) {
                if (curHeight < h) { // non-leaf level
                    if (curLevel < Math.pow(2, curHeight - 1)) { // not full in non-leaf level
                        return false;
                    } else {
                        int nextLevel = 0;
                        for (int i = 0; i < curLevel; i++) {
                            TreeNode cur = q.poll();

                            q.offer(cur.left);
                            nextLevel++;

                            q.offer(cur.right);
                            nextLevel++;
                        }
                        curLevel = nextLevel;
                        curHeight++;
                    }
                } else { // leaf level
                    for (int i = 0; i < curLevel; i++) {
                        if (q.poll() == null) {
                            if (i < nullIdx) {
                                nullIdx = i;
                            }
                        } else {
                            if (valIdx == -1) {
                                valIdx = i;
                            }
                        }
                    }
                }
            }

            return (nullIdx > valIdx); // false (not complete binary tree) when null is in left of value in leaf level
        }
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    // https://ryancmu.gitbooks.io/lintcode/content/complete_binary_tree.html
}
