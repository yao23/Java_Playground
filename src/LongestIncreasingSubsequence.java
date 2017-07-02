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

// find-next-higher-element-in-an-array-for-each-element
// [1 3 2 4 1] => [3 , 4,  4, -1, -1]

