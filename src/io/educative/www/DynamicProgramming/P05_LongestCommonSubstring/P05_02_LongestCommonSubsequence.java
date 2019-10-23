package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_02_LongestCommonSubsequence {

    public int findLCSLength(String s1, String s2) {
        return findLCSLengthRecursive(s1, s2, 0, 0);
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^{m+n}), where ‘m’ and ‘n’ are the lengths of the
     * two input strings. The space complexity is O(n+m) which is used to store the recursion stack.
     *
     * @param s1
     * @param s2
     * @param i1
     * @param i2
     * @return
     */
    private int findLCSLengthRecursive(String s1, String s2, int i1, int i2) {
        if(i1 == s1.length() || i2 == s2.length())
            return 0;

        if(s1.charAt(i1) == s2.charAt(i2))
            return 1 + findLCSLengthRecursive(s1, s2, i1+1, i2+1);

        int c1 = findLCSLengthRecursive(s1, s2, i1, i2+1);
        int c2 = findLCSLengthRecursive(s1, s2, i1+1, i2);

        return Math.max(c1, c2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param s1
     * @param s2
     * @return
     */
    public int findLCSLengthV1(String s1, String s2) {
        Integer[][] dp = new Integer[s1.length()][s2.length()];
        return findLCSLengthRecursiveV1(dp, s1, s2, 0, 0);
    }

    private int findLCSLengthRecursiveV1(Integer[][] dp, String s1, String s2, int i1, int i2) {
        if (i1 == s1.length() || i2 == s2.length())
            return 0;

        if (dp[i1][i2] == null) {
            if (s1.charAt(i1) == s2.charAt(i2))
                dp[i1][i2] = 1 + findLCSLengthRecursiveV1(dp, s1, s2, i1 + 1, i2 + 1);
            else {
                int c1 = findLCSLengthRecursiveV1(dp, s1, s2, i1, i2 + 1);
                int c2 = findLCSLengthRecursiveV1(dp, s1, s2, i1 + 1, i2);
                dp[i1][i2] = Math.max(c1, c2);
            }
        }

        return dp[i1][i2];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time and space complexity of the algorithm is O(m*n) where ‘m’ and ‘n’ are the lengths of the two input strings.
     *
     * @param s1
     * @param s2
     * @return
     */
    public int findLCSLengthV2(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);

                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }

    /**
     * O(n) space complexity
     *
     * @param s1
     * @param s2
     * @return
     */
    public int findLCSLengthV3(String s1, String s2) {
        int[][] dp = new int[2][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i%2][j] = 1 + dp[(i-1)%2][j-1];
                else
                    dp[i%2][j] = Math.max(dp[(i-1)%2][j], dp[i%2][j-1]);

                maxLength = Math.max(maxLength, dp[i%2][j]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        P05_02_LongestCommonSubsequence lcs = new P05_02_LongestCommonSubsequence();
        System.out.println(lcs.findLCSLength("abdca", "cbda"));
        System.out.println(lcs.findLCSLength("passport", "ppsspt"));
    }
}