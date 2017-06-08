/**
 * Created by liyao on 6/7/17.
 */
import java.util.*;

public class BinaryTreeInorderTraversal {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) { // processing left children
                stack.push(cur);
                cur = cur.left;
            } else { // left part is done
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }

        return result;
    }

    public static List<Integer> inorderTraversalV0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                if (cur.left == null) {
                    result.add(cur.val);
                    if (cur.right != null) {
                        stack.push(cur.right);
                        cur = null;
                    } else {
                        if (stack.isEmpty()) {
                            break;
                        } else {
                            cur = stack.pop();
                            if (cur != null) {
                                result.add(cur.val);
                                cur = cur.right;
                            }
                        }
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

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        List<Integer> result = inorderTraversal(node0);

        for (int element : result) {
            System.out.print(element + " ");
        }

        node0.left = null;
        node0.right = node1;
        node1.left = node2;
        node1.right = null;
        node2.left = null;
        node2.right = null;

        System.out.println();
        result = inorderTraversal(node0);

        for (int element : result) {
            System.out.print(element + " ");
        }

        node0.left = null;
        node0.right = node1;
        node1.left = null;
        node1.right = node2;
        node2.left = null;
        node2.right = null;

        System.out.println();
        result = inorderTraversal(node0);

        for (int element : result) {
            System.out.print(element + " ");
        }
    }

    // [1,2,3,4,5,6,7]
    // [1,null,2,3]
    // [1,null,2,null,3]
    // [2,3,null,1]
}
