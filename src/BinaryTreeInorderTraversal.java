/**
 * Created by liyao on 6/7/17.
 */
import java.util.*;

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                if (cur.left == null) {
                    result.add(cur.val);
                    if (cur.right != null) {
                        stack.push(cur.right);
                    } else {
                        cur = stack.pop();
                    }
                } else {
                    stack.push(cur);
                    cur = cur.left;
                }
            } else {
                cur = stack.pop();
            }
        }

        return result;
    }
}
