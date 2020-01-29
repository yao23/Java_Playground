package io.educative.www.Coding.P07_TreeBFS;

import java.util.*;

class P07_10_TreeBoundary {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(N), where ‘N’ is the total number of nodes in the tree.
     * This is due to the fact that we traverse each node twice, one for BFS and one for DFS.
     *
     * Space complexity
     * The space complexity of the above algorithm will be O(N) as we need to return a list containing the boundary nodes.
     * We will also need O(N) space for the queue. Since we can have a maximum of N/2 nodes at any level (this could
     * happen only at the lowest level of a complete binary tree), therefore we will need O(N) space to store them in
     * the queue. We will also need O(N) space for DFS.
     *
     * @param root
     * @return
     */
    public static List<TreeNode> findBoundary(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<TreeNode> leftView = new ArrayList<>();
        List<TreeNode> rightView = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode.left == null && currentNode.right == null) // skip leaf nodes
                    continue;
                else if (i == 0) // if it is the first node of this level, add it to the leftView
                    leftView.add(currentNode);
                else if (i == levelSize - 1) { // if it is the last node of this level, add it to the rightView
                    // because of ant-clockwise direction, we need to populate rightView in the reverse direction
                    rightView.add(0, currentNode);
                }

                // insert the children of current node in the queue
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
        }

        return new ArrayList<TreeNode>() {
            {
                addAll(leftView);
                addAll(findLeavesDFS(root));
                addAll(rightView);
            }
        };
    }

    private static List<TreeNode> findLeavesDFS(TreeNode root) {
        List<TreeNode> leaves = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            if (currentNode.left == null && currentNode.right == null)
                leaves.add(currentNode);

            // insert the children of current node in the stack
            // add the right child first, this way left child will be at the top and processed first
            if (currentNode.right != null)
                stack.push(currentNode.right);
            if (currentNode.left != null)
                stack.push(currentNode.left);
        }
        return leaves;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(9);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(15);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(6);
        List<TreeNode> result = P07_10_TreeBoundary.findBoundary(root);
        for (TreeNode node : result) {
            System.out.print(node.val + " ");
        }
    }
}
