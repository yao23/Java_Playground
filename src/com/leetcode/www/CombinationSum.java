package com.leetcode.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum { // LC 39
    /**
     * [2, 3, 6, 7], 7 => [[7],[2, 2, 3]]
     * beats 58.30%
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            list.add(nums[i]);
            if (remain >= nums[i]) {
                helper(res, list, nums, i, remain - nums[i]);
            }
            list.remove(list.size() - 1);
        }
    }
}



