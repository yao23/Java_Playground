package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k == 0)	{
            return result;
        }
        List<Integer> combinations = new ArrayList<>();
        int depth = 1;
        generateCombination(n, k, depth, combinations, result);
        return result;
    }

    private void generateCombination(int n, int k, int depth, List<Integer> combinations,
                                    List<List<Integer>> result) {
        if (combinations.size() == k) {
            List<Integer> tmp = new ArrayList<>();
            tmp.addAll(combinations);
            result.add(tmp);
            return;
        }
        for (int i = depth; i <= n; i++) {
            combinations.add(i);
            generateCombination(n, k, i + 1, combinations, result);
            combinations.remove(combinations.size() - 1);
        }
    }
}
