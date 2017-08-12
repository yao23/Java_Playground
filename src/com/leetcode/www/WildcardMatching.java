package com.leetcode.www;

public class WildcardMatching { // LC 44
    /**
     *
     * @param str
     * @param pattern
     * @return
     *
     * For each element in s
     * If *s == *p or *p == ? which means this is a match, then goes to next element s++ p++.
     * If p == '*', this is also a match, but one or many chars may be available, so let us save this *'s position and the matched s position.
     * If not match, then we check if there is a * previously showed up,
     * if there is no *,  return false;
     * if there is an *,  we set current p to the next element of *, and set current s to the next saved s position.

     * e.g.

       abed
       ?b*d**

       a=?, go on, b=b, go on,
       e=*, save * position star=3, save s position ss = 3, p++
       e!=d,  check if there was a *, yes, ss++, s=ss; p=star+1
       d=d, go on, meet the end.
       check the rest element in p, if all are *, true, else false;
     */
    public boolean isMatch(String str, String pattern) { // beats 95.12% (linear runtime and constant space)
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()) {
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            p++;
        }

        return p == pattern.length();
    }

    public boolean isMatchV2(String s, String p) { // beats 58.79% (from end to beginning)
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] match = new boolean[lenS + 1][lenP + 1];
        match[lenS][lenP] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) != '*') {
                break;
            } else {
                match[lenS][i] = true;
            }
        }
        for (int i = lenS - 1; i >= 0; i--) {
            for (int j = lenP - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    match[i][j] = match[i + 1][j + 1];
                } else if(p.charAt(j) == '*') {
                    match[i][j] = match[i + 1][j] || match[i][j + 1]; // * match multiple or empty
                } else {
                    match[i][j] = false;
                }
            }
        }
        return match[0][0];
    }

    /**
     *
     * @param s
     * @param p
     * @return
     *
     * The original post has DP 2d array index from high to low, which is not quite intuitive.

     * Below is another 2D dp solution. Ideal is identical.

     * dp[i][j] denotes whether s[0....i-1] matches p[0.....j-1],

     * First, we need to initialize dp[i][0], i= [1,m]. All the dp[i][0] should be false because p has nothing in it.

     * Then, initialize dp[0][j], j = [1, n]. In this case, s has nothing, to get dp[0][j] = true, p must be '*', '*', '**',etc. Once p.charAt(j-1) != '*', all the dp[0][j] afterwards will be false.

     * Then start the typical DP loop.

     * Though this solution is clear and easy to understand. It is not good enough in the interview. it takes O(mn) time and O(mn) space.

     * Improvement: 1) optimize 2d dp to 1d dp, this will save space, reduce space complexity to O(N). 2) use iterative 2-pointer.
     */
    public boolean isMatchV1(String s, String p) { // beats 61.43% (from beginning to end)
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }

        for (int j = 1; j<= n; j++) {
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public boolean isMatchV0(String s, String p) { // not working
        if (s == null || p == null) {
            return false;
        }
        int lenS = s.length();
        int lenP = p.length();
        if (lenS == 0 && lenP == 0) {
            return true;
        } else {
            boolean[][] dp = new boolean[lenS + 1][lenP + 1];
            dp[0][0] = true;
            for (int j = 1; j <= lenP; j++) {
                if (dp[0][j - 1] && p.charAt(j) == '?' || p.charAt(j) == '*') {
                    dp[0][j] = true;
                }
            }
            for (int i = 0; i < lenS; i++) {
                for (int j = 0; j < lenP; j++) {
                    if (p.charAt(j) == '*') {
                        dp[i + 1][j  + 1] = dp[i][j];
                    } else {
                        if (p.charAt(j + 1) == s.charAt(i + 1) || p.charAt(j + 1) == '?' || p.charAt(j + 1) == '*') {
                            dp[i + 1][j  + 1] = dp[i][j];
                        } else {
                            dp[i + 1][j  + 1] = false;
                        }
                    }
                }
            }
            return dp[lenS][lenP];
        }
    }
}
