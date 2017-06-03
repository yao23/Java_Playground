/**
 * Created by liyao on 6/2/17.
 */

public class SymmetricTree {
    private static boolean helperFunc(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            if (left.val == right.val) {
                return (helperFunc(left.left, right.right) && helperFunc(left.right, right.left));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return helperFunc(root.left, root.right);
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

        System.out.println("Tree 1 is symmetric: " + isSymmetric(node0));

        node1.left = null;
        node1.right = node3;
        node2.left = null;
        node2.right = node6;

        System.out.println("Tree 2 is symmetric: " + isSymmetric(node0));
    }
}
