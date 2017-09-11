package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle { // LC 118
    public List<List<Integer>> generate(int numRows) { // beats 19.61%
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }
        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);
        if (numRows == 1) {
            return result;
        }
        List<Integer> second = new ArrayList<>();
        second.add(1);
        second.add(1);
        result.add(second);
        if (numRows == 2) {
            return result;
        }
        for (int i = 3; i <= numRows; i++) {
            int resultSize = result.size();
            List<Integer> previous = result.get(resultSize - 1);
            List<Integer> current = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j== 1 || j == i) {
                    current.add(1);
                } else {
                    int num = previous.get(j - 2) + previous.get(j - 1);
                    current.add(num);
                }
            }
            result.add(current);
        }
        return result;
    }
}

// 0 => []
// given numRows = 5 =>
// [
//      [1],
//     [1,1],
//    [1,2,1],
//   [1,3,3,1],
//  [1,4,6,4,1]
// ]