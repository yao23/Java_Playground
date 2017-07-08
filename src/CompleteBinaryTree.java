/**
 * Created by liyao on 7/7/17.
 */

import java.util.LinkedList;

public class CompleteBinaryTree {
    public boolean isComplete(TreeNode root) {
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
}
