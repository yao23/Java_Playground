/**
 * Created by liyao on 7/2/17.
 */
public class LongestCommonSubsequence {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        char arr1[] = A.toCharArray();
        char arr2[] = B.toCharArray();

        return helper(arr1, arr2, arr1.length, arr2.length);
    }

    private int helper(char[] arr1, char[] arr2, int idx1, int idx2) {
        if (idx1 == 0 || idx2 == 0) { // out of bound
            return 0;
        } else {
            if (arr1[idx1 - 1] == arr2[idx2 - 1]) { // contribute to longest common subsequence
                return 1 + helper(arr1, arr2, idx1 - 1, idx2 - 1);
            } else {
                return Math.max(helper(arr1, arr2, idx1 - 1, idx2), helper(arr1, arr2, idx1, idx2 - 1));
            }
        }
    }
}
