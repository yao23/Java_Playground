/**
 * Created by liyao on 7/1/17.
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    // brute force: 2 for loops, 1st to iterate (n - 1) elements, 2nd to calculate larger nums after the current one
}