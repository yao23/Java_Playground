/**
 * Created by liyao on 6/2/17.
 */

import java.util.ArrayList;

public class SymmetricTree {
    private static boolean recursiveFunc(TreeNode left, TreeNode right) { // recursive
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            if (left.val == right.val) {
                return (recursiveFunc(left.left, right.right) && recursiveFunc(left.right, right.left));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean iterativeFunc(TreeNode left, TreeNode right) { // iterative
        ArrayList<TreeNode> leftList = new ArrayList<TreeNode>();
        ArrayList<TreeNode> rightList = new ArrayList<TreeNode>();
        leftList.add(left);
        rightList.add(right);
        while (!leftList.isEmpty() && !rightList.isEmpty()) {
            TreeNode leftNode = leftList.remove(0);
            TreeNode rightNode = rightList.remove(0);
            if (leftNode.val == rightNode.val) {
                leftList.add(left.left);
                leftList.add(left.right);
                rightList.add(right.right);
                rightList.add(right.left);
            } else {
                return false;
            }
        }

        return (leftList.isEmpty() && rightList.isEmpty());
    }

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return recursiveFunc(root.left, root.right);
//            return iterativeFunc(root.left, root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(3);

        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        System.out.println("Tree 1 is symmetric: " + isSymmetric(node0)); // beats 24.60%

        node1.left = null;
        node1.right = node3;
        node2.left = null;
        node2.right = node6;

        System.out.println("Tree 2 is symmetric: " + isSymmetric(node0));
    }
}
