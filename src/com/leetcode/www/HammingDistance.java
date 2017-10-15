package com.leetcode.www;

public class HammingDistance { // LC 461
    /**
     * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) { // beats 72.94%
        int xor = x ^ y;
        int res = 0;
        while (xor != 0) {
            res += (xor & 1);
            xor >>= 1;
        }
        return res;
    }

    // XOR
    public int hammingDistanceV0(int x, int y) { // beats 38.47%
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
