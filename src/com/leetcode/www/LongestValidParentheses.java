package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses { // LC 32

    /**
     * DP solution
     *
     * The idea is go through the string and use DP to store the longest valid parentheses at that point.
     * take example "()(())"
     * i : [0,1,2,3,4,5]
     * s : [( ,) ,( ,( ,) ,) ]
     * DP:[0,2,0,0,2,6]

     * 1, We count all the ‘(‘.
     * 2, If we find a ‘)’ and ‘(‘ counter is not 0, we have at least a valid result size of 2. “()"
     * 3, Check the the one before (i - 1). If DP[i - 1] is not 0 means we have something like this “(())” . This will have DP “0024"
     * 4, We might have something before "(())”. Take "()(())” example, Check the i = 1 because this might be a consecutive valid string.
     *
     * https://discuss.leetcode.com/topic/35776/two-java-solutions-with-explanation-stack-dp-short-easy-to-understand/2
     */

    public int longestValidParentheses(String s) { // beats 82.73% (DP)
        int len = s.length();
        int[] dp = new int[len];
        int result = 0;
        int leftCount = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (leftCount > 0) {
                dp[i] = dp[i - 1] + 2;
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                result = Math.max(result, dp[i]);
                leftCount--;
            }
        }
        return result;
    }

    public int longestValidParenthesesV0(String s) { // beats 36.25% (stack, 1 pass)
        int max = 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(')', -1));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(new Node(c, i));
            } else {
                Node top = stack.peek();
                if (top.c == '(') {
                    stack.pop();
                    max = Math.max(max, i - stack.peek().i);
                } else {
                    stack.push(new Node(c, i));
                }
            }
        }
        return max;
    }

    class Node {
        char c;
        int i;
        public Node(char c, int i) {
            this.c = c;
            this.i = i;
        }
    }
}
