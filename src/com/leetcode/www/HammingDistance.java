package com.leetcode.www;

public class HammingDistance { // LC 461
    /**
     * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     *
     * @param x
     * @param y
     * @return
     */
    // XOR
    public int hammingDistance(int x, int y) { // beats 38.47%
        return Integer.bitCount(x ^ y);
    }
}

// Input: x = 1, y = 4
// Output: 2
// Explanation:
// 1   (0 0 0 1)
// 4   (0 1 0 0)
//        ↑   ↑
// The above arrows point to positions where the corresponding bits are different.
