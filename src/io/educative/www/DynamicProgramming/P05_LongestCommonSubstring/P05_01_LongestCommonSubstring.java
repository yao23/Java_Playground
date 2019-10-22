package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_01_LongestCommonSubstring {

    public int findLCSLength(String s1, String s2) {
        return findLCSLengthRecursive(s1, s2, 0, 0, 0);
    }

    /**
     * Because of the three recursive calls, the time complexity of the above algorithm is exponential O(3^{m+n}),
     * where ‘m’ and ‘n’ are the lengths of the two input strings. The space complexity is O(m+n), this space will be
     * used to store the recursion stack.
     *
     * @param s1
     * @param s2
     * @param i1
     * @param i2
     * @param count
     * @return
     */
    private int findLCSLengthRecursive(String s1, String s2, int i1, int i2, int count) {
        if(i1 == s1.length() || i2 == s2.length())
            return count;

        if(s1.charAt(i1) == s2.charAt(i2))
            count = findLCSLengthRecursive(s1, s2, i1+1, i2+1, count+1);

        int c1 = findLCSLengthRecursive(s1, s2, i1, i2+1, 0);
        int c2 = findLCSLengthRecursive(s1, s2, i1+1, i2, 0);

        return Math.max(count, Math.max(c1, c2));
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param s1
     * @param s2
     * @return
     */
    public int findLCSLengthV1(String s1, String s2) {
        int maxLength = Math.max(s1.length(), s2.length());
        Integer[][][] dp = new Integer[s1.length()][s2.length()][maxLength];
        return findLCSLengthRecursiveV1(dp, s1, s2, 0, 0, 0);
    }

    private int findLCSLengthRecursiveV1(Integer[][][] dp, String s1, String s2, int i1, int i2, int count) {
        if(i1 == s1.length() || i2 == s2.length())
            return count;

        if(dp[i1][i2][count] == null) {
            int c1 = count;
            if(s1.charAt(i1) == s2.charAt(i2))
                c1 = findLCSLengthRecursiveV1(dp, s1, s2, i1+1, i2+1, count+1);
            int c2 = findLCSLengthRecursiveV1(dp, s1, s2, i1, i2+1, 0);
            int c3 = findLCSLengthRecursiveV1(dp, s1, s2, i1+1, i2, 0);
            dp[i1][i2][count] = Math.max(c1, Math.max(c2, c3));
        }

        return dp[i1][i2][count];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time and space complexity of the algorithm is O(m*n), where ‘m’ and ‘n’ are the lengths of the two input strings.
     *
     * @param s1
     * @param s2
     * @return
     */
    public int findLCSLength(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
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
    public int findLCSLength(String s1, String s2) {
        int[][] dp = new int[2][s2.length()+1];
        int maxLength = 0;
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                dp[i%2][j] = 0;
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i%2][j] = 1 + dp[(i-1)%2][j-1];
                    maxLength = Math.max(maxLength, dp[i%2][j]);
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        P05_01_LongestCommonSubstring lcs = new P05_01_LongestCommonSubstring();
        System.out.println(lcs.findLCSLength("abdca", "cbda"));
        System.out.println(lcs.findLCSLength("passport", "ppsspt"));
    }
}
