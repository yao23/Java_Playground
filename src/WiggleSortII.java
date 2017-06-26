/**
 * Created by liyao on 6/25/17.
 */
public class WiggleSortII {
    private void findIndex(int i, int[] nums, int len, int left, int right) {
        if (i % 2 == 0) { // even index, should less than left and right values
            if (i > 0) {
                if (nums[i - 1] <= nums[i] || i + 1 < len && nums[i] >= nums[1]) {
                    left = i;
                } else {
//                    continue;
                }
            } else { // i == 0
                if (i + 1 < len && nums[i] >= nums[1]) {
                    left = i;
                } else {
//                    continue;
                }
            }
        } else { // odd index, should greater than left and right values

        }
    }
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;

        while (left < right) {

        }
    }
}
