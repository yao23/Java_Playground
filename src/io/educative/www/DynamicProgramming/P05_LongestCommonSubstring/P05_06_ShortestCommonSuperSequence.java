package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_05_ShortestCommonSuperSequence {

    public int findSCSLength(String s1, String s2) {
        return findSCSLengthRecursive(s1, s2, 0, 0);
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^{n+m}), where ‘n’ and ‘m’ are the lengths of the
     * input sequences. The space complexity is O(n+m) which is used to store the recursion stack.
     *
     * @param s1
     * @param s2
     * @param i1
     * @param i2
     * @return
     */
    private int findSCSLengthRecursive(String s1, String s2, int i1, int i2) {
        // if we have reached the end of a string, return the remaining length of the other string,
        // as in this case we have to take all of the remaining other string
        if(i1 == s1.length())
            return s2.length()-i2;
        if(i2 == s2.length())
            return s1.length()-i1;

        if(s1.charAt(i1) == s2.charAt(i2))
            return 1 + findSCSLengthRecursive(s1, s2, i1+1, i2+1);

        int length1 = 1 + findSCSLengthRecursive(s1, s2, i1, i2+1);
        int length2 = 1 + findSCSLengthRecursive(s1, s2, i1+1, i2);

        return Math.min(length1, length2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param s1
     * @param s2
     * @return
     */
    public int findSCSLengthV1(String s1, String s2) {
        Integer[][] dp = new Integer[s1.length()][s2.length()];
        return findSCSLengthRecursiveV1(dp, s1, s2, 0, 0);
    }

    private int findSCSLengthRecursiveV1(Integer[][] dp, String s1, String s2, int i1, int i2) {
        // if we have reached the end of a string, return the remaining length of the other string,
        // as in this case we have to take all of the remaining other string
        if(i1 == s1.length())
            return s2.length()-i2;
        if(i2 == s2.length())
            return s1.length()-i1;

        if(dp[i1][i2] == null) {
            if(s1.charAt(i1) == s2.charAt(i2))
                dp[i1][i2] = 1 + findSCSLengthRecursiveV1(dp, s1, s2, i1+1, i2+1);
            else {
                int length1 = 1 + findSCSLengthRecursiveV1(dp, s1, s2, i1, i2+1);
                int length2 = 1 + findSCSLengthRecursiveV1(dp, s1, s2, i1+1, i2);
                dp[i1][i2] = Math.min(length1, length2);
            }
        }

        return dp[i1][i2];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time and space complexity of the algorithm is O(n*m).
     *
     * @param s1
     * @param s2
     * @return
     */
    public int findSCSLength(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        // if one of the strings is of zero length, SCS would be equal to the length of the other string
        for(int i=0; i<=s1.length(); i++)
            dp[i][0] = i;
        for(int j=0; j<=s2.length(); j++)
            dp[0][j] = j;

        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        P05_05_ShortestCommonSuperSequence scs = new P05_05_ShortestCommonSuperSequence();
        System.out.println(scs.findSCSLength("abcf", "bdcf"));
        System.out.println(scs.findSCSLength("dynamic", "programming"));
    }
}
