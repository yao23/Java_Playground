package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class Combinations { // LC 77
    public List<List<Integer>> combine(int n, int k) { // beats 86.29%
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k > n || k < 0) {
            return result;
        }
        if (k == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        result = combine(n - 1, k - 1);
        for (List<Integer> list : result) {
            list.add(n);
        }
        result.addAll(combine(n - 1, k));
        return result;
    }

    public List<List<Integer>> combineV0(int n, int k) { // beats 24.86%
        List<List<Integer>> result = new ArrayList<>();
        if (k == 0)	{
            return result;
        }
        List<Integer> combinations = new ArrayList<>();
        int depth = 1;
        generateCombination(n, k, depth, combinations, result);
        return result;
    }

    private void generateCombination(int n, int k, int depth, List<Integer> combinations, List<List<Integer>> result) {
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

// n = 4 and k = 2,
// [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4]]