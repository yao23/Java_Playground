/**
 * Created by liyao on 7/5/17.
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else {
            int[] results = new int[len]; // max in cur position
            int max = 0;
            for (int i = 0; i < len; i++) {
                int preMax = 0;
                for (int j = 0; j < i - 1; j++) {
                    if (results[j] > preMax) {
                        preMax = results[j];
                    }
                }
                results[i] = (nums[i] + preMax);

                if (results[i] > max) {
                    max = results[i];
                }
            }

            return max;
        }
    }

    public int robV1(int[] nums) { // beats 40.29%
        int preNo = 0, preYes = 0; // previous one rob no and yes
        for (int n : nums) {
            int tmp = preNo;
            preNo = Math.max(preNo, preYes);
            preYes = tmp + n;
        }

        return Math.max(preNo, preYes);
    }

    // [] => 0
    // [1] => 1
    // [1,2] => 2
    // [1,2,3] => 4
    // [1,5,3] => 5
    // [2,7,9,3,1] => 12

    // beats 4.82%
}