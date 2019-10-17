package io.educative.www.DynamicProgramming.P04_LongestPalindromicSubsequence;

class P04_01_LongestPalindromicSubsequence {

    public int findLPSLength(String st) {
        return findLPSLengthRecursive(st, 0, st.length()-1);
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^n), where ‘n’ is the length of the input sequence.
     * The space complexity is O(n) which is used to store the recursion stack.
     * @param st
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int findLPSLengthRecursive(String st, int startIndex, int endIndex) {
        if(startIndex > endIndex)
            return 0;

        // every sequence with one element is a palindrome of length 1
        if(startIndex == endIndex)
            return 1;

        // case 1: elements at the beginning and the end are the same
        if(st.charAt(startIndex) == st.charAt(endIndex))
            return 2 + findLPSLengthRecursive(st, startIndex+1, endIndex-1);

        // case 2: skip one element either from the beginning or the end
        int c1 =  findLPSLengthRecursive(st, startIndex+1, endIndex);
        int c2 =  findLPSLengthRecursive(st, startIndex, endIndex-1);
        return Math.max(c1, c2);
    }

    public int findLPSLengthV1(String st) {
        Integer[][] dp = new Integer[st.length()][st.length()];
        return findLPSLengthRecursiveV1(dp, st, 0, st.length()-1);
    }

    /**
     * Since our memoization array dp[st.length()][st.length()] stores the results for all the subproblems, we can
     * conclude that we will not have more than N*N subproblems (where ‘N’ is the length of the input sequence). This
     * means that our time complexity will be O(N^2).
     *
     * The above algorithm will be using O(N^2) space for the memoization array. Other than that we will use O(N)
     * space for the recursion call-stack. So the total space complexity will be O(N^2 + N), which is asymptotically
     * equivalent to O(N^2).
     *
     * @param dp
     * @param st
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int findLPSLengthRecursiveV1(Integer[][] dp, String st, int startIndex, int endIndex) {
        if(startIndex > endIndex)
            return 0;

        // every sequence with one element is a palindrome of length 1
        if(startIndex == endIndex)
            return 1;

        if(dp[startIndex][endIndex] == null) {
            // case 1: elements at the beginning and the end are the same
            if(st.charAt(startIndex) == st.charAt(endIndex)) {
                dp[startIndex][endIndex] = 2 + findLPSLengthRecursive(dp, st, startIndex+1, endIndex-1);
            } else {
                // case 2: skip one element either from the beginning or the end
                int c1 =  findLPSLengthRecursive(dp, st, startIndex+1, endIndex);
                int c2 =  findLPSLengthRecursive(dp, st, startIndex, endIndex-1);
                dp[startIndex][endIndex] = Math.max(c1, c2);
            }
        }

        return dp[startIndex][endIndex];
    }

    /**
     * The time and space complexity of the below algorithm is O(n^2), where ‘n’ is the length of the input sequence.
     * @param st
     * @return
     */
    public int findLPSLengthV2(String st) {
        // dp[i][j] stores the length of LPS from index 'i' to index 'j'
        int[][] dp = new int[st.length()][st.length()];

        // every sequence with one element is a palindrome of length 1
        for (int i = 0; i < st.length(); i++)
            dp[i][i] = 1;

        for (int startIndex = st.length() - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < st.length(); endIndex++) {
                // case 1: elements at the beginning and the end are the same
                if (st.charAt(startIndex) == st.charAt(endIndex)) {
                    dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];
                } else { // case 2: skip one element either from the beginning or the end
                    dp[startIndex][endIndex] = Math.max(dp[startIndex + 1][endIndex], dp[startIndex][endIndex - 1]);
                }
            }
        }
        return dp[0][st.length() - 1];
    }

    public static void main(String[] args) {
        P04_01_LongestPalindromicSubsequence lps = new P04_01_LongestPalindromicSubsequence();
        System.out.println(lps.findLPSLength("abdbca"));
        System.out.println(lps.findLPSLength("cddpd"));
        System.out.println(lps.findLPSLength("pqr"));
    }
}