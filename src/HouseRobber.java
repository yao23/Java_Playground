/**
 * Created by liyao on 7/5/17.
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else {
            int[] results = new int[len];
            for (int i = 0; i < len; i++) {
                int max = 0;
                for (int j = 0; j < i - 1; j++) {
                    if (nums[j] > max) {
                        max = nums[j];
                    }
                }
                results[i] = (nums[i] + max);
            }

            return results[len - 1];
        }
    }

    // [] => 0
    // [1] => 1
    // [1,2] => 2
    // [1,2,3] => 4
    // [1,5,3] => 5
}
