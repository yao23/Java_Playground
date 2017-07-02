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

    private static int lengthOfLISV1(int[] nums) { // O(n^2)
        if (nums == null) {
            return 0;
        } else if (nums.length <= 1) {
            return nums.length;
        } else {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1); // min num is 1 (self)

            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) { // contribute to increasing subsequence
                        if (dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }

            int result = 1;
            for (int num : dp) {
                if (num > result) {
                    result = num;
                } else {
                    continue;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println(lengthOfLIS(nums));

        System.out.println(lengthOfLISV1(nums));
    }

    // [] => 0
    // [1] => 1
    // [1,2] => 2
    // [2,1] => 1
    // [10,9,2,5,3,7,101,18] => 4 ([2,3,7,18])

    // beats 65.09% (O(nlogn))
    // brute force: 2 for loops, 1st to iterate (n - 1) elements, 2nd to calculate larger nums after the current one
}