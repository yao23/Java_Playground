package com.leetcode.www;

public class DecodeWays {
    /**
     *
     *  "" => 0
     * // "0" => 0
     * // "1" => 1 ('A')
     * // "12" => 2 ('AB' or 'L')
     * @param s
     * @return
     */
    public int numDecodings(String s) { // beats 54.66%
        int len = s.length();
        if (0 == len || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < len + 1; i++ ) {
            char curChar = s.charAt(i - 1);
            int curNum = curChar - '0';
            if (curNum == 0 ) {	// s[i] is not valid
                String TwoNum = s.substring(i - 2, i);
                if( Integer.parseInt(TwoNum) <= 26 && Integer.parseInt(TwoNum) >= 10 ) { // s[i - 1][i] is valid
                    dp[i] = dp[i - 2];
                } else {
                    dp[i] = 0;
                }
            } else { // s[i] is valid
                String TwoNum = s.substring(i - 2, i);
                if (Integer.parseInt(TwoNum) <= 26 && Integer.parseInt(TwoNum) >= 10 ) { // s[i - 1][i] is valid
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }

        return dp[len];
    }

    public int numDecodingsV0(String s) { // beats 6.01%
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = (s.charAt(0) != '0') ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i-1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }

        return dp[n];
    }
}
