/**
 * Created by liyao on 7/1/17.
 */
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else {
            int[] dp = new int[nums.length]; // longest increasing subsequence
            int len = 0;

            for (int x : nums) {
                int i = Arrays.binarySearch(dp, 0, len, x);
                if (i < 0) {
                    i = -(i + 1); // insertion position
                }
                dp[i] = x;
                if (i == len) { // insertion position could be added to increase subsequence
                    len++;
                }
            }

            return len;
        }
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println(lengthOfLIS(nums));
    }

    // [10,9,2,5,3,7,101,18] => 4 ([2,3,7,101])
    // brute force: 2 for loops, 1st to iterate (n - 1) elements, 2nd to calculate larger nums after the current one
}