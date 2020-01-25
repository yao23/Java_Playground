package io.educative.www.Coding.P07_TreeBFS;

import java.util.*;

class P07_06_LevelOrderSuccessor {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(N), where ‘N’ is the total number of nodes in the tree.
     * This is due to the fact that we traverse each node once.
     *
     * Space complexity
     * The space complexity of the above algorithm will be O(N) which is required for the queue. Since we can have a
     * maximum of N/2 nodes at any level (this could happen only at the lowest level), therefore we will need O(N)
     * space to store them in the queue.
     *
     * @param root
     * @param key
     * @return
     */
    public static TreeNode findSuccessor(TreeNode root, int key) {
        if (root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            // insert the children of current node in the queue
            if (currentNode.left != null)
                queue.offer(currentNode.left);
            if (currentNode.right != null)
                queue.offer(currentNode.right);

            // break if we have found the key
            if (currentNode.val == key)
                break;
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        TreeNode result = P07_06_LevelOrderSuccessor.findSuccessor(root, 12);
        if (result != null)
            System.out.println(result.val + " ");
        result = P07_06_LevelOrderSuccessor.findSuccessor(root, 9);
        if (result != null)
            System.out.println(result.val + " ");
    }
}
