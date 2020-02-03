package io.educative.www.Coding.P08_TreeDFS;

import java.util.*;

class P08_05_CountPathsForASum {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(N^2) in the worst case, where ‘N’ is the total number of nodes
     * in the tree. This is due to the fact that we traverse each node once, but for every node, we iterate the current
     * path. The current path, in the worst case, can be O(N) (in the case of a skewed tree). But, if the tree is balanced,
     * then the current path will be equal to the height of the tree, i.e., O(logN). So the best case of our algorithm
     * will be O(NlogN).
     *
     * Space complexity
     * The space complexity of the above algorithm will be O(N). This space will be used to store the recursion stack.
     * The worst case will happen when the given tree is a linked list (i.e., every node has only one child). We also
     * need O(N) space for storing the currentPath in the worst case.
     *
     * Overall space complexity of our algorithm is O(N).
     *
     * @param root
     * @param S
     * @return
     */
    public static int countPaths(TreeNode root, int S) {
        List<Integer> currentPath = new LinkedList<>();
        return countPathsRecursive(root, S, currentPath);
    }

    private static int countPathsRecursive(TreeNode currentNode, int S, List<Integer> currentPath) {
        if (currentNode == null)
            return 0;

        // add the current node to the path
        currentPath.add(currentNode.val);
        int pathCount = 0, pathSum = 0;
        // find the sums of all sub-paths in the current path list
        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        while (pathIterator.hasPrevious()) {
            pathSum += pathIterator.previous();
            // if the sum of any sub-path is equal to 'S' we increment our path count.
            if (pathSum == S) {
                pathCount++;
            }
        }

        // traverse the left sub-tree
        pathCount += countPathsRecursive(currentNode.left, S, currentPath);
        // traverse the right sub-tree
        pathCount += countPathsRecursive(currentNode.right, S, currentPath);

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);

        return pathCount;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + P08_05_CountPathsForASum.countPaths(root, 11));
    }
}
