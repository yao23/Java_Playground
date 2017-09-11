package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII { // LC 119
    public List<Integer> getRow(int rowIndex) { // beats 88.48%
        List<Integer> results = new ArrayList<>();
        if (rowIndex == 0) {
            results.add(1);
            return results;
        } else if (rowIndex == 1) {
            results.add(1);
            results.add(1);
            return results;
        }
        int[] nums = new int[rowIndex + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= rowIndex; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == 0 || j == i) {
                    nums[j] = 1;
                } else {
                    nums[j] = nums[j] + nums[j - 1];
                }
            }
        }
        for (int i = 0; i <= rowIndex; i++) {
            results.add(nums[i]);
        }
        return results;
    }
}

// 0 => [1]
// 3 => [1,3,3,1]