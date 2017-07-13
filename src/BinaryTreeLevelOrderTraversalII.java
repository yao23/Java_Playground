/**
 * Created by liyao on 7/12/17.
 */

import java.util.*;

public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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

        Collections.reverse(result);
        return result;
    }

    // [3,9,20,null,null,15,7] => [[15,7],[9,20],[3]]

    // beats 23.97%
}
