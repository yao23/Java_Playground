package io.educative.www.Coding.P07_TreeBFS;

import java.util.*;

class P07_04_LevelAveragesInABinaryTree {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(N), where ‘N’ is the total number of nodes in the tree. This is
     * due to the fact that we traverse each node once.
     *
     * Space complexity
     * The space complexity of the above algorithm will be O(N) which is required for the queue. Since we can have a
     * maximum of N/2 nodes at any level (this could happen only at the lowest level), therefore we will need O(N)
     * space to store them in the queue.
     *
     * @param root
     * @return
     */
    public static List<Double> findLevelAverages(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // add the node's value to the running sum
                levelSum += currentNode.val;
                // insert the children of current node to the queue
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
            // append the current level's average to the result array
            result.add(levelSum / levelSize);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Double> result = P07_04_LevelAveragesInABinaryTree.findLevelAverages(root);
        System.out.print("Level averages are: " + result);
    }
}
