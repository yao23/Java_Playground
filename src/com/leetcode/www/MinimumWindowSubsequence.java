import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumWindowSubsequence { // 727 [Google]
    class Solution {
        /**
         * Dynamic Programming (Postfix Variation)
         *
         * Time Complexity: O(ST), where S, T are the lengths of S, T. We have two for-loops.
         * Space Complexity: O(S), the length of dp.
         *
         * @param S
         * @param T
         * @return
         */
        public String minWindow(String S, String T) {
            int[][] dp = new int[2][S.length()];

            for (int i = 0; i < S.length(); ++i)
                dp[0][i] = S.charAt(i) == T.charAt(0) ? i : -1;

        /*At time j when considering T[:j+1],
          the smallest window [s, e] where S[e] == T[j]
          is represented by dp[j & 1][e] = s, and the
          previous information of the smallest window
          [s, e] where S[e] == T[j-1] is stored as
          dp[~j & 1][e] = s.
        */
            for (int j = 1; j < T.length(); ++j) {
                int last = -1;
                Arrays.fill(dp[j & 1], -1);
                //Now we would like to calculate the candidate windows
                //"dp[j & 1]" for T[:j+1].  'last' is the last window seen.
                for (int i = 0; i < S.length(); ++i) {
                    if (last >= 0 && S.charAt(i) == T.charAt(j))
                        dp[j & 1][i] = last;
                    if (dp[~j & 1][i] >= 0)
                        last = dp[~j & 1][i];
                }
            }

            //Looking at the window data dp[~T.length & 1],
            //choose the smallest length window [s, e].
            int start = 0, end = S.length();
            for (int e = 0; e < S.length(); ++e) {
                int s = dp[~T.length() & 1][e];
                if (s >= 0 && e - s < end - start) {
                    start = s;
                    end = e;
                }
            }
            return end < S.length() ? S.substring(start, end+1) : "";
        }
    }

    /**
     * Dynamic Programming (Next Array Variation)
     *
     * Time Complexity: O(ST), where S, T are the lengths of S, T, and assuming a fixed-sized alphabet. The precomputation
     * of nxt is O(S), and the other work happens in two for-loops.
     *
     * Space Complexity: O(S), the size of windows.
     *
     * @param S
     * @param T
     * @return
     */
    public String minWindow(String S, String T) {
        int N = S.length();
        int[] last = new int[26];
        int[][] nxt = new int[N][26];
        Arrays.fill(last, -1);

        for (int i = N - 1; i >= 0; --i) {
            last[S.charAt(i) - 'a'] = i;
            for (int k = 0; k < 26; ++k) {
                nxt[i][k] = last[k];
            }
        }

        List<int[]> windows = new ArrayList();
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == T.charAt(0))
                windows.add(new int[]{i, i});
        }
        for (int j = 1; j < T.length(); ++j) {
            int letterIndex = T.charAt(j) - 'a';
            for (int[] window: windows) {
                if (window[1] < N-1 && nxt[window[1]+1][letterIndex] >= 0) {
                    window[1] = nxt[window[1]+1][letterIndex];
                }
                else {
                    window[0] = window[1] = -1;
                    break;
                }
            }
        }

        int[] ans = {-1, S.length()};
        for (int[] window: windows) {
            if (window[0] == -1) break;
            if (window[1] - window[0] < ans[1] - ans[0]) {
                ans = window;
            }

        }
        return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
    }
}

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple
 * such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 *
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 *
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 *
 */
