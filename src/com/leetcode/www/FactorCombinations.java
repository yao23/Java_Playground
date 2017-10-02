package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FactorCombinations { // LC 254
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> getFactors(int n) { // beats 95.67%
        getFactors(n, new ArrayList<>(), 2);
        return result;
    }

    private void getFactors(int n, List<Integer> iList, int st){
        for (int i = st; i * i <= n; ++i) { // only iterate until square root of n
            if (n % i == 0) {
                iList.add(i);
                iList.add(n / i);
                result.add(new ArrayList<>(iList));
                iList.remove(iList.size() - 1);
                getFactors(n / i, iList, i);
                iList.remove(iList.size() - 1);
            }
        }
    }

    // top down (stack)
    public List<List<Integer>> getFactorsV1(int n) { // beats 74.68%

        List<List<Integer>> res = new ArrayList<>();
        combination(n, 2, new ArrayDeque<>(), res);
        return res;
    }

    private void combination(int n, int start, Deque<Integer> stack, List<List<Integer>> res) {
        int end = (int)Math.sqrt(n);
        for (int i = start; i <= end; i++) {
            if (n % i != 0) {
                continue;
            }
            res.add(new ArrayList<>(stack));
            res.get(res.size() - 1).add(i);
            res.get(res.size() - 1).add(n / i);

            stack.push(i);
            combination(n / i, i, stack, res);
            stack.pop();
        }
    }

    // bottom up
    public List<List<Integer>> getFactorsV0(int n) { // beats 32.09%
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), n, 2);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
        if (n <= 1) {
            if (item.size() > 1) {
                result.add(new ArrayList<>(item));
            }
            return;
        }

        for (int i = start; i <= n; ++i) {
            if (n % i == 0) {
                item.add(i);
                helper(result, item, n / i, i);
                item.remove(item.size() - 1);
            }
        }
    }
}
