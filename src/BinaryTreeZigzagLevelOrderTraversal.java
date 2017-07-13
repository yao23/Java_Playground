/**
 * Created by liyao on 7/12/17.
 */

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) { // beats 38.80%
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
            return result; // 不必把下面的大部分code放到 else 中
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
                            q.offer(curNode.right); // 不能在这里改变queue啊，自己debug一下看看就知道为什么了 (it will affect q.removeLast() get curNode)
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
