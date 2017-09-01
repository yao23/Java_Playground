/**
 * Created by liyao on 7/5/17.
 */
public class HouseRobberII { // LC 213
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        } else {
            return Math.max(robRange(nums, 0, len - 2), robRange(nums, 1, len - 1));
        }
    }

    private int robRange(int[]nums, int start, int end) {
        int preNo = 0, preYes = 0;
        for (int i = start; i <= end; i++) {
            int tmp = preNo;
            preNo = Math.max(preNo, preYes);
            preYes = nums[i] + tmp;
        }

        return Math.max(preNo, preYes);
    }

    // [] => 0
    // [1] => 1
    // [1,3,5,2,4] => 9

    // beats 60.24%
}