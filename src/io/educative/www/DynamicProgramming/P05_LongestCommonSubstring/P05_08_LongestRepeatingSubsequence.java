package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_08_LongestRepeatingSubsequence {

    public int findLRSLength(String str) {
        return findLRSLengthRecursive(str, 0, 0);
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^n), where ‘n’ is the length of the input sequence.
     * The space complexity is O(n) which is used to store the recursion stack.
     *
     * @param str
     * @param i1
     * @param i2
     * @return
     */
    private int findLRSLengthRecursive(String str, int i1, int i2) {
        if(i1 == str.length() || i2 == str.length())
            return 0;

        if(i1 != i2 && str.charAt(i1) == str.charAt(i2))
            return 1 + findLRSLengthRecursive(str, i1+1, i2+1);

        int c1 = findLRSLengthRecursive(str, i1, i2+1);
        int c2 = findLRSLengthRecursive(str, i1+1, i2);

        return Math.max(c1, c2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param str
     * @return
     */
    public int findLRSLengthV1(String str) {
        Integer[][] dp = new Integer[str.length()][str.length()];
        return findLRSLengthRecursiveV1(dp, str, 0, 0);
    }

    private int findLRSLengthRecursiveV1(Integer[][] dp, String str, int i1, int i2) {
        if(i1 == str.length() || i2 == str.length())
            return 0;

        if(dp[i1][i2] == null) {
            if(i1 != i2 && str.charAt(i1) == str.charAt(i2))
                dp[i1][i2] = 1 + findLRSLengthRecursiveV1(dp, str, i1+1, i2+1);
            else {
                int c1 = findLRSLengthRecursiveV1(dp, str, i1, i2+1);
                int c2 = findLRSLengthRecursiveV1(dp, str, i1+1, i2);
                dp[i1][i2] = Math.max(c1, c2);
            }
        }

        return dp[i1][i2];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time and space complexity of the algorithm is O(n^2), where ‘n’ is the length of the input sequence.
     *
     * @param str
     * @return
     */
    public int findLRSLengthV2(String str) {
        int[][] dp = new int[str.length()+1][str.length()+1];
        int maxLength = 0;
        // dp[i1][i2] will be storing the LRS up to str[0..i1-1][0..i2-1]
        // this also means that subsequences of length zero (first row and column of dp[][]),
        // will always have LRS of size zero.
        for(int i1=1; i1 <= str.length(); i1++) {
            for(int i2=1; i2 <= str.length(); i2++) {
                if(i1 != i2 && str.charAt(i1-1) == str.charAt(i2-1))
                    dp[i1][i2] = 1 + dp[i1-1][i2-1];
                else
                    dp[i1][i2] = Math.max(dp[i1-1][i2], dp[i1][i2-1]);

                maxLength = Math.max(maxLength, dp[i1][i2]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        P05_08_LongestRepeatingSubsequence lrs = new P05_08_LongestRepeatingSubsequence();
        System.out.println(lrs.findLRSLength("tomorrow"));
        System.out.println(lrs.findLRSLength("aabdbcec"));
        System.out.println(lrs.findLRSLength("fmff"));
    }
}