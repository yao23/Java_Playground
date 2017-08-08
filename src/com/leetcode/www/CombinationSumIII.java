package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII { // LC 216
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> list, int start, int remain, int k) {
        if (list.size() == k && remain == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (remain < 2 * i && remain != i) { // skip smaller and impossible one
                continue;
            }
            list.add(i);
            helper(res, list, i + 1, remain - i, k); // go to next level
            list.remove(list.size() - 1);
        }
    }
}

// k = 3, n = 7 => [[1,2,4]]
// k = 3, n = 9 => [[1,2,6], [1,3,5], [2,3,4]]

// beats 47.84%