package com.leetcode.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII { // LC 40
    /**
     * [10, 1, 2, 7, 6, 1, 5], 8 => [[1, 7],[1, 2, 5],[2, 6],[1, 1, 6]]
     * beats 66.82%
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(res, new ArrayList<>(), candidates, 0, target);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int start, int remain) {
        if (remain == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) { // skip duplicate
                continue;
            }
            list.add(nums[i]);
            if (remain >= nums[i]) {
                helper(res, list, nums, i + 1, remain - nums[i]); // go to next level
            }
            list.remove(list.size() - 1);
        }
    }
}

