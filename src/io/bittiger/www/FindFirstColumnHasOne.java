package io.bittiger.www;

public class FindFirstColumnHasOne {
    public int getFirstColumnContainsOne(int[][] nums) {
        if (nums == null || nums.length == 0) { // corner case
            return -1;
        }
        // normal case
        int row = nums.length;
        int col = nums[0].length;
        int res = Integer.MAX_VALUE;
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if (nums[i][j] == 0) {
                i++;
            } else {
                res = j;
                j--;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res + 1;
    }
}