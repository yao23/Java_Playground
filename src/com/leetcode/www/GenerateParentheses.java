package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses { // LC 22
    public List<String> generateParenthesis(int n) { // beats 29.14%
        List<String> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        generateParenthesis(n, n, "", results);
        return results;
    }

    private void generateParenthesis(int left, int right, String cur, List<String> results) {
        if (left == 0) {
            while (right > 0) {
                cur += ")";
                right--;
            }
            results.add(cur);
            return;
        }

        generateParenthesis(left - 1, right, cur + "(", results);

        if (left < right) {
            generateParenthesis(left, right - 1, cur + ")", results);
        }
    }
}

// n = 3

// [
//    "((()))",
//    "(()())",
//    "(())()",
//    "()(())",
//    "()()()"
// ]