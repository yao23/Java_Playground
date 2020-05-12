package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators { // LC 282 (FB)
    /**
     * Backtrace
     *     1. overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
     *     2. 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
     *     3. a little trick is that we should save the value that is to be multiplied in the next recursion.
     *
     * Runtime: 91 ms, faster than 59.21% of Java online submissions for Expression Add Operators.
     * Memory Usage: 40.5 MB, less than 78.38% of Java online submissions for Expression Add Operators.
     *
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) { // beats 68.98%
        List<String> res = new ArrayList<>();

        if (num == null || num.length() == 0) {
            return res;
        }

        helper(res, "", num, target, 0, 0, 0);

        return res;
    }

    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed) { // long to avoid overflow
        if (pos == num.length()) {
            if (target == eval) {
                rst.add(path);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') { // i passed pos and pos is '0' (start with '0')
                break;
            }
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(rst, path + cur, num, target, i + 1, cur, cur); // no operator in path to avoid case like "+1"
            } else {
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);

                helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);

                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur ); // 1 + 2 * 3 = 1 + 2 - 2 + 2 * 3
            }
        }

        // If have a sequence of 12345 and you have proceeded to 1 + 2 + 3, now your eval is 6 right?
        // If you want to add a * between 3 and 4, you would take 3 as the digit to be multiplied,
        // so you want to take it out from the existing eval. You have 1 + 2 + 3 * 4 and the eval now is (1 + 2 + 3) - 3 + (3 * 4).
    }

    public List<String> addOperatorsV0(String num, int target) {
        // collect all possible num combinations
        List<List<String>> numList = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        collectNums(num, 0, tmp, numList);
        // calculate after add operators
        List<String> res = new ArrayList<>();
        calculate(numList, res, target);
        return res;
    }

    private void calculate(List<List<String>> numList, List<String> res, int target) {
        for (List<String> nums : numList) {
            int numOprtrComb = (int)Math.pow(3, nums.size() - 1); // +, -, *
            for (int i = 0; i < numOprtrComb; i++) {
                if (getTarget(i, nums, target)) {
                    res.add(getExpression(i, nums));
                }
            }
        }
    }

    private String getExpression(int i, List<String> nums) {
        // concatenate nums and operators
        return "";
    }

    private boolean getTarget(int i, List<String> nums, int target) {
        // concatenate nums and operators, calculate, compare with target
        return true;
    }

    private void collectNums(String num, int depth, List<String> tmp, List<List<String>> numList) {
        if (depth == num.length()) {
            numList.add(new ArrayList<>(tmp));
        } else {
            for (int i = depth; i < num.length(); i++) {
                String cur = num.substring(depth, i + 1);
                if (isValidStr(cur)) {
                    tmp.add(cur);
                    collectNums(num, i + 1, tmp, numList);
                    tmp.remove(tmp.size() - 1);
                } else {
                    return;
                }
            }
        }
    }

    // 0 sequences in head
    private boolean isValidStr(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == '0') {
                return false;
            }
        }

        return true;
    }
}
