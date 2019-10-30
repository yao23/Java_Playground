package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_09_SubsequencePatternMatching {

    public int findSPMCount(String str, String pat) {
        return findSPMCountRecursive(str, pat, 0, 0);
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^{m+n}), where ‘m’ and ‘n’ are the lengths of the
     * string and pattern respectively. The space complexity is O(n+m) which is used to store the recursion stack.
     *
     * @param str
     * @param pat
     * @param strIndex
     * @param patIndex
     * @return
     */
    private int findSPMCountRecursive(String str, String pat, int strIndex, int patIndex) {

        // if we have reached the end of the pattern
        if(patIndex == pat.length())
            return 1;

        // if we have reached the end of the string but pattern has still some characters left
        if(strIndex == str.length())
            return 0;

        int c1 = 0;
        if(str.charAt(strIndex) == pat.charAt(patIndex))
            c1 = findSPMCountRecursive(str, pat, strIndex+1, patIndex+1);

        int c2 = findSPMCountRecursive(str, pat, strIndex+1, patIndex);

        return c1 + c2;
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param str
     * @param pat
     * @return
     */
    public int findSPMCountV1(String str, String pat) {
        Integer[][] dp = new Integer[str.length()][pat.length()];
        return findSPMCountRecursiveV1(dp, str, pat, 0, 0);
    }

    private int findSPMCountRecursiveV1(Integer[][] dp, String str, String pat, int strIndex, int patIndex) {

        // if we have reached the end of the pattern
        if(patIndex == pat.length())
            return 1;

        // if we have reached the end of the string but pattern has still some characters left
        if(strIndex == str.length())
            return 0;

        if(dp[strIndex][patIndex] == null) {
            int c1 = 0;
            if(str.charAt(strIndex) == pat.charAt(patIndex))
                c1 = findSPMCountRecursiveV1(dp, str, pat, strIndex+1, patIndex+1);
            int c2 = findSPMCountRecursiveV1(dp, str, pat, strIndex+1, patIndex);
            dp[strIndex][patIndex] = c1 + c2;
        }

        return dp[strIndex][patIndex];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time and space complexity of the algorithm is O(m*n), where ‘m’ and ‘n’ are the lengths of the string and
     * the pattern respectively.
     *
     * @param str
     * @param pat
     * @return
     */
    public int findSPMCountV2(String str, String pat) {
        // every empty pattern has one match
        if(pat.length() == 0)
            return 1;

        if(str.length() == 0 || pat.length() > str.length())
            return 0;

        // dp[strIndex][patIndex] will be storing the count of SPM up to str[0..strIndex-1][0..patIndex-1]
        int[][] dp = new int[str.length()+1][pat.length()+1];

        // for the empty pattern, we have one matching
        for(int i=0; i<=str.length(); i++)
            dp[i][0] = 1;

        for(int strIndex=1; strIndex<=str.length(); strIndex++) {
            for(int patIndex=1; patIndex<=pat.length(); patIndex++) {
                if(str.charAt(strIndex-1) == pat.charAt(patIndex-1))
                    dp[strIndex][patIndex] = dp[strIndex-1][patIndex-1];
                dp[strIndex][patIndex] += dp[strIndex-1][patIndex];
            }
        }

        return dp[str.length()][pat.length()];
    }

    public static void main(String[] args) {
        P05_09_SubsequencePatternMatching spm = new P05_09_SubsequencePatternMatching();
        System.out.println(spm.findSPMCount("baxmx", "ax"));
        System.out.println(spm.findSPMCount("tomorrow", "tor"));
    }
}