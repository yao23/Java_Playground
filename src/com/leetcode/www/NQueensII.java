package com.leetcode.www;

public class NQueensII { // LC 52
    private int numSol;
    private int upper;

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for N-Queens II.
     * Memory Usage: 32 MB, less than 72.04% of Java online submissions for N-Queens II.
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        numSol = 0;
        upper = (1 << n) - 1 ;
        nQueens(0, 0, 0);
        return numSol;
    }

    // ld for left diagonal and rd for right diagonal
    private void nQueens(int row, int ld, int rd){
        int pos, p;
        if (row != upper) { // some rows need to be filled
            pos = upper & (~(row | ld | rd));	// available slot for Q
            while (pos != 0) { // "1" for available slot
                p = pos & (-pos); // from right to left, the first "1" in pos
                // now occupy the most right available position
                pos = pos - p; // now place Q，pos is kind of like a available slot marker
                nQueens(row + p, (ld + p) << 1, (rd + p) >> 1);
            }
        } else {
            numSol++;
        }
    }
}

// beats 98.71%
