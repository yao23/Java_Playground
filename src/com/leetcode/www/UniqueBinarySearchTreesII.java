package com.leetcode.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueBinarySearchTreesII { // LC 95
    /**
     * Choose a root
     * Construct a Tree
     * Construct all Trees Recursively
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        int[] count = new int[n + 1];
        count[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                count[i] += (count[j] * count[i - 1 - j]);
            }
        }

        TreeNode[] roots = helper(1, n, count);
        return Arrays.asList(roots);
    }

    private TreeNode[] helper(int start, int end, int[] count) {
        if (start > end) {
            TreeNode[] roots = new TreeNode[1];
            roots[0] = null;
            return roots;
        }

        TreeNode[] roots = new TreeNode[count[end - start + 1]];
        int index = 0;

        for (int i = start; i <= end; i++) {
            TreeNode[] left = helper(start, i - 1, count);
            TreeNode[] right = helper(i + 1, end, count);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    roots[index] = new TreeNode(i);
                    roots[index].left = l;
                    roots[index].right = r;
                    index++;
                }
            }
        }

        return roots;
    }
}

// beats 98.72%

// time: O(mn), space: O(n ^ 2)

/**
    Given n = 3, your program should return all 5 unique BST's shown below.

        1         3     3      2      1
         \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
 */