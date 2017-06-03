/**
 * Created by liyao on 6/2/17.
 */

import java.util.LinkedList;

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

    private static boolean iterativeFunc(TreeNode leftInput, TreeNode rightInput) { // iterative
        // ArrayList takes time to remove element from head
        // ArrayDeque cannot add null element (NullPointerException)
        // LinkedList can add null element and easy to remove head element

        LinkedList<TreeNode> leftList = new LinkedList<TreeNode>();
        LinkedList<TreeNode> rightList = new LinkedList<TreeNode>();

        leftList.add(leftInput);
        rightList.add(rightInput);

        while (!leftList.isEmpty() && !rightList.isEmpty()) {

            TreeNode leftNode = leftList.poll();
            TreeNode rightNode = rightList.poll();

            if (leftNode != null && rightNode != null) {
                if (leftNode.val == rightNode.val) {
                    leftList.add(leftNode.left);
                    leftList.add(leftNode.right);

                    rightList.add(rightNode.right);
                    rightList.add(rightNode.left);
                } else {
                    return false;
                }
            } else {
                return (leftNode == null && rightNode == null);
            }
        }

        return true; // Linked List can add null element, so no case for one list is empty and anther is not
    }

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return recursiveFunc(root.left, root.right);
        }
    }

    private static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return iterativeFunc(root.left, root.right);
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

        System.out.println("Tree 1 is symmetric (recursive): " + isSymmetric(node0)); // beats 24.60%
        System.out.println("Tree 1 is symmetric (iterative): " + isSymmetric1(node0)); //

        node1.left = null;
        node1.right = node3;
        node2.left = null;
        node2.right = node6;

        System.out.println("Tree 2 is symmetric (recursive): " + isSymmetric(node0));
        System.out.println("Tree 2 is symmetric (iterative): " + isSymmetric1(node0));

        node0 = new TreeNode(9);
        node1 = new TreeNode(-42);
        node2 = new TreeNode(-42);
        node3 = new TreeNode(76);
        node4 = new TreeNode(76);
        node5 = new TreeNode(13);
        node6 = new TreeNode(13);

        node0.left = node1;
        node0.right = node2;
        node1.left = null;
        node1.right = node3;
        node2.left = node4;
        node2.right = null;
        node3.left = null;
        node3.right = node5;
        node4.left = null;
        node4.right = node6;

        System.out.println("Tree 3 is symmetric (recursive): " + isSymmetric(node0));
        System.out.println("Tree 3 is symmetric (iterative): " + isSymmetric1(node0));
    }
}
