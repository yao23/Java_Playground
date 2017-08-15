package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class Permutations { // LC 46
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        helper(nums, res, list, 0);
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, List<Integer> list, int pos) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            swap(nums, pos, i);
            helper(nums, res, list, pos + 1); // pos + 1 (pos: cur level)
            swap(nums, pos, i);
            list.remove(list.size() - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// [1,2,3] => [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

// beats 90.66%
