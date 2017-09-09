package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class GrayCode { // LC 89
    public List<Integer> grayCode(int n) { // beats 34.48%
        List<Integer> result = new ArrayList<>();
        int num_count = 1 << n;
        for (int i = 0; i < num_count; i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }

    private List<Integer> grayCode2(int n) {
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            result.add(0);
            return result;
        }
        if (n == 1) {
            result.add(0);
            result.add(1);
            return result;
        }

        List<Integer> prev = grayCode2(n - 1);
        result.addAll(prev);
        int top = 1 << (n - 1);
        for (int i = prev.size() - 1; i >= 0; i--) {
            result.add(top + prev.get(i));
        }
        return result;
    }
}

// given n = 2, return [0,1,3,2]. Its gray code sequence is:
// 00 - 0
// 01 - 1
// 11 - 3
// 10 - 2