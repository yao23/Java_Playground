package com.leetcode.www;

import java.util.Arrays;

public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i] = (s.charAt(i) == '0') ? 0 : 1;
        }

        for (int i = 1; i < s.length(); i++) {
            char pre = s.charAt(i - 1);
            char cur = s.charAt(i);

            if (cur == '0') {
                if (pre == '0') {
                    return 0;
                } else {
                    dp[i] = dp[i - 1];
                }
            } else {
                if (pre == '0') {
                    dp[i] = dp[i - 1];
                } else {
                    String sub = s.substring(i - 1, i + 1);
                    int num = Integer.valueOf(sub);
                    if (10 <= num && num <= 26) {
                        if (i < 2) {
                            dp[i] = dp[i - 1] + 1;
                        } else {
                            dp[i] = dp[i - 1] + dp[i - 2];
                        }
                    } else {
                        dp[i] = dp[i - 1];
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }
}

// "" => 0
// "0" => 0
// "1" => 1 ('A')
// "12" => 2 ('AB' or 'L')