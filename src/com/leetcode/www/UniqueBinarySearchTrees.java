package com.leetcode.www;

public class UniqueBinarySearchTrees { // LC 96
    public int numTrees(int n) { // beats 9.90%
        if (n <= 0) {
            return 0;
        }
        int[] root = new int[n + 1];
        return count(n, root);
    }

    private int count(int n, int[] root) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (root[n] != 0) {
            return root[n];
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (count(i, root) * count(n - 1 - i, root));
        }
        root[n] = sum;
        return sum;
    }
}

/**
    Given n = 3, there are a total of 5 unique BST's.

        1         3     3      2      1
         \       /     /      / \      \
         3     2      1      1   3      2
        /     /       \                  \
       2     1         2                  3
*/
